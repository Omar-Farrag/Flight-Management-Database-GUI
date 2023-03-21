package DatabaseManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.*;

public class ConstraintChecker implements ConstraintChecks {

    private String metaDataFile = "Metadata.json";
    private ArrayList<String> currentApplicationTables;
    private FileWriter fout;
    private ResultSet constraintsTable;
    private ResultSet tableDataTypes;
    private Connection conn;
    private Validator validator;

    private HashMap<String, JSONObject> table_to_object;
    private static ConstraintChecker instance;

    public static ConstraintChecker getInstance() {
        return instance == null ? instance = new ConstraintChecker() : instance;
    }

    private ConstraintChecker() {
        conn = DatabaseManager.getInstance().getConn();
        currentApplicationTables = new ArrayList<>();
        for (Table t : Table.values())
            currentApplicationTables.add(t.getTableName().toUpperCase());

        validator = new Validator();
        table_to_object = new HashMap<>();

        if (!new File(metaDataFile).exists())
            initMetaDataFile();
        else
            readMetaDataFromFile();
    }

    private void readMetaDataFromFile() {
        try {
            FileInputStream fin = new FileInputStream(metaDataFile);
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bin = new BufferedReader(in);

            String fileContent = "";
            String line;
            while ((line = bin.readLine()) != null)
                fileContent += line;

            JSONArray metaData = (JSONArray) new JSONParser().parse(fileContent);
            for (Object table : metaData) {
                JSONObject tableJSONObject = (JSONObject) table;
                table_to_object.put(tableJSONObject.get("TableName").toString(), tableJSONObject);
            }

            bin.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Errors checkInsertion(Table t, AttributeCollection toInsert)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        return check(t, null, toInsert);

    }

    @Override
    public Errors checkUpdate(Table t, AttributeCollection primaryKey, AttributeCollection toUpdate)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        return check(t, primaryKey, toUpdate);
    }

    @Override
    public Errors checkRetrieval(Table t, AttributeCollection toGet, Filter filter)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    private Errors check(Table t, AttributeCollection primaryKey, AttributeCollection toValidate)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        JSONObject table = getTableInfoFromMetaData(t.getTableName());
        JSONObject tableAttributes = (JSONObject) table.get("Attributes");
        Errors errors = new Errors();

        for (Attribute attribute : toValidate.attributes()) {
            if (!tableAttributes.containsKey(attribute.getAttributeName()))
                throw new AttributeNotFoundException(t.getTableName(), attribute.getAttributeName());

            JSONArray attributeConstraints = (JSONArray) tableAttributes.get(attribute.getAttributeName());
            for (Object obj : attributeConstraints) {

                String constraint = (String) obj;
                errors.add(attribute, validator.validate(constraint, primaryKey, attribute, toValidate));
            }
        }
        return errors;
    }

    private JSONObject getTableInfoFromMetaData(String tableName) throws TableNotFoundException {
        if (!table_to_object.containsKey(tableName))
            throw new TableNotFoundException();
        else
            return table_to_object.get(tableName);
    }

    // ///////////////////////////////////////////METADATA EXTRACTION FROM
    // DB/////////////////////////////////////////////////
    private Boolean initMetaDataFile() {
        try {
            fout = new FileWriter(metaDataFile);
            JSONArray metaData = new JSONArray();

            String formattedTableNames = "'" + String.join(",", currentApplicationTables).replace(",", "','") + "'";
            constraintsTable = DatabaseManager.getInstance().executeStatement(
                    " SELECT U.TABLE_NAME," +
                            "U.COLUMN_NAME," +
                            "CONSTRAINT_TYPE," +
                            " SEARCH_CONDITION," +
                            " R_CONSTRAINT_NAME" +
                            " FROM USER_CONS_COLUMNS U" +
                            " JOIN ALL_CONSTRAINTS A" +
                            " ON ( U.TABLE_NAME = A.TABLE_NAME" +
                            " AND U.CONSTRAINT_NAME = A.CONSTRAINT_NAME )" +
                            " WHERE U.OWNER = '" + DatabaseManager.getInstance().getUsername() + "'" +
                            " AND A.OWNER = '" + DatabaseManager.getInstance().getUsername() + "'" +
                            " AND U.TABLE_NAME in (" + formattedTableNames + ")");

            tableDataTypes = DatabaseManager.getInstance().executeStatement(
                    " select *" +
                            " from user_tab_columns" +
                            " order by TABLE_NAME,column_id"

            );

            extractTables(metaData);

            fout.write(metaData.toJSONString());
            fout.flush();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private void extractTables(JSONArray metaData) {

        try {
            DatabaseMetaData meta = conn.getMetaData();

            for (String tableName : currentApplicationTables) {
                JSONObject table = new JSONObject();
                table.put("TableName", tableName);

                JSONObject attributes = extractAttributesForTable(tableName, meta);

                table.put("Attributes", attributes);
                metaData.add(table);
                table_to_object.put(tableName, table);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private JSONObject extractAttributesForTable(String tableName, DatabaseMetaData meta) throws SQLException {
        ResultSet columns = meta.getColumns(null, "B00087320", tableName, null);
        JSONObject attributes = new JSONObject();

        while (columns.next()) {

            String attributeName = columns.getString("COLUMN_NAME");
            JSONArray constraints = new JSONArray();

            ArrayList<String> constraintStrings = extractConstraints(tableName, attributeName);
            constraints.add(extractDataType(columns.getRow(), tableName));

            for (String constraint : constraintStrings)
                constraints.add(constraint);

            attributes.put(attributeName, constraints);
        }

        return attributes;
    }

    private String extractDataType(int rowNumber, String tableName) {
        try {

            tableDataTypes.beforeFirst();
            while (tableDataTypes.next()) {
                if (tableDataTypes.getString("TABLE_NAME").equals(tableName)) {

                    tableDataTypes.absolute(tableDataTypes.getRow() + rowNumber - 1);
                    String dataType = tableDataTypes.getString("DATA_TYPE");
                    if (dataType.toLowerCase().equals("number"))
                        dataType += extractPrecisionAndScale(tableDataTypes);
                    else if (dataType.toLowerCase().equals("varchar2") || dataType.toLowerCase().equals("char"))
                        dataType += extractMaxLength(tableDataTypes);
                    return dataType;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";

    }

    private String extractMaxLength(ResultSet tableDescription) throws SQLException {
        return "_" + tableDescription.getString("CHAR_COL_DECL_LENGTH");
    }

    private String extractPrecisionAndScale(ResultSet tableDescription) throws SQLException {
        return "_" + tableDescription.getString("DATA_PRECISION") + "_" + tableDescription.getString("DATA_SCALE");
    }

    private ArrayList<String> extractConstraints(String tableName, String columnName) throws SQLException {

        ArrayList<String> constraints = new ArrayList<>();
        constraintsTable.first();
        while (constraintsTable.next()) {
            if (constraintsTable.getString("TABLE_NAME").equals(tableName)
                    && constraintsTable.getString("COLUMN_NAME").equals(columnName)) {

                String constraint = constraintsTable.getString("CONSTRAINT_TYPE").toUpperCase();

                if (constraint.equals("C"))
                    constraint += "_"
                            + constraintsTable.getString("SEARCH_CONDITION").replace("\"", "").replace("\n", "")
                                    .replace("   ", "");
                else if (constraint.equals("R"))
                    constraint += "_" + constraintsTable.getString("R_CONSTRAINT_NAME");

                constraints.add(constraint);
            }
        }
        return constraints;
    }

    public class Errors {
        private HashMap<Attribute, ArrayList<String>> attribute_to_errors;

        private Errors() {
            attribute_to_errors = new HashMap<>();
        }

        private void add(Attribute attribute, String errorMessage) {
            if (errorMessage.isEmpty())
                return;

            if (!attribute_to_errors.containsKey(attribute))
                attribute_to_errors.put(attribute, new ArrayList<String>());

            attribute_to_errors.get(attribute).add(errorMessage);

        }

        public boolean noErrors() {
            return attribute_to_errors.isEmpty();
        }

        public ArrayList<String> getErrorByAttribute(Attribute attribute) throws UnvalidatedAttributeException {
            if (!attribute_to_errors.containsKey(attribute))
                throw new UnvalidatedAttributeException(attribute);
            else
                return attribute_to_errors.get(attribute);
        }

    }

    public static void main(String[] args) {
        new ConstraintChecker();
    }
}
