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

    public JSONArray metaData;
    private HashMap<Constraint, ValidationFunction> constraint_to_validator;
    private static ConstraintChecker instance;

    public ConstraintChecker getInstance() {
        return instance == null ? instance = new ConstraintChecker() : instance;
    }

    private ConstraintChecker() {
        conn = DatabaseManager.getInstance().getConn();
        currentApplicationTables = new ArrayList<>();
        for (Table t : Table.values())
            currentApplicationTables.add(t.getTableName().toUpperCase());

        if (!new File(metaDataFile).exists())
            initMetaDataFile();
        else
            readMetaDataFromFile();

        initConstraintsToValidatorMap();
    }

    private void initConstraintsToValidatorMap() {
        constraint_to_validator = new HashMap<>();

        constraint_to_validator.put(new Constraint(ConstraintEnum.PRIMARY),
                (constraint) -> validatePRIMARY(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.UNIQUE),
                (constraint) -> validateUNIQUE(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.FOREIGN),
                (constraint) -> validateFOREIGN(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.LESS_THAN),
                (constraint) -> validateLESS_THAN(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.GREATER_THAN),
                (constraint) -> validateGREATER_THAN(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.EQUAL),
                (constraint) -> validateEQUAL(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.NOT_EQUAL),
                (constraint) -> validateNOT_EQUAL(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.LESS_EQUAL),
                (constraint) -> validateLESS_EQUAL(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.GREATER_EQUAL),
                (constraint) -> validateGREATER_EQUAL(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.NOT_NULL),
                (constraint) -> validateNOT_NULL(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.LIKE),
                (constraint) -> validateLIKE(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.BETWEEN),
                (constraint) -> validateBETWEEN(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.IN),
                (constraint) -> validateIN(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.REGEXP_LIKE),
                (constraint) -> validateREGEXP_LIKE(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.NUMBER),
                (constraint) -> validateNUMBER(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.FLOAT),
                (constraint) -> validateFLOAT(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.CHAR),
                (constraint) -> validateCHAR(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.VARCHAR2),
                (constraint) -> validateVARCHAR2(constraint));
        constraint_to_validator.put(new Constraint(ConstraintEnum.DATE),
                (constraint) -> validateDATE(constraint));
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

            metaData = (JSONArray) new JSONParser().parse(fileContent);
            bin.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Errors check(Table t, AttributeCollection attributesToCheck)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        JSONObject table = getTableInfoFromMetaData(t.getTableName());
        JSONObject tableAttributes = (JSONObject) table.get("Attributes");

        for (Attribute a : attributesToCheck.attributes()) {
            String attribute = a.getAttributeName();
            if (!tableAttributes.containsKey(attribute))
                throw new AttributeNotFoundException(t.getTableName(), attribute);

            JSONArray attributeConstraints = (JSONArray) tableAttributes.get(attribute);
            for (Object obj : attributeConstraints) {
                String constraint = (String) obj;

                if (!constraint_to_validator.containsKey(constraint))
                    throw new ConstraintNotFoundException(constraint);

                else {
                    ValidationFunction validator = constraint_to_validator.get(constraint);
                    validator.validate(constraint);
                }
            }
        }
        return new Errors();

    }

    private JSONObject getTableInfoFromMetaData(String tableName) throws TableNotFoundException {
        for (Object table : metaData) {
            JSONObject tableObject = (JSONObject) table;
            if (tableObject.get("TableName").equals(tableName))
                return tableObject;
        }
        throw new TableNotFoundException();
    }

    // ///////////////////////////////////////////METADATA EXTRACTION FROM
    // DB/////////////////////////////////////////////////
    private Boolean initMetaDataFile() {
        try {
            fout = new FileWriter(metaDataFile);
            metaData = new JSONArray();

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

            extractTables();

            fout.write(metaData.toJSONString());
            fout.flush();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private void extractTables() {

        try {
            DatabaseMetaData meta = conn.getMetaData();

            for (String tableName : currentApplicationTables) {
                JSONObject table = new JSONObject();
                table.put("TableName", tableName);

                JSONObject attributes = extractAttributesForTable(tableName, meta);

                table.put("Attributes", attributes);
                metaData.add(table);
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

    // ///////////////////////////////////////////VALIDATORS/////////////////////////////////////////////////

    private String validatePRIMARY(String constraint) {
        return "";
    }

    private String validateUNIQUE(String constraint) {
        return "";
    }

    private String validateFOREIGN(String constraint) {
        return "";
    }

    private String validateLESS_THAN(String constraint) {
        return "";
    }

    private String validateGREATER_THAN(String constraint) {
        return "";
    }

    private String validateEQUAL(String constraint) {
        return "";
    }

    private String validateNOT_EQUAL(String constraint) {
        return "";
    }

    private String validateLESS_EQUAL(String constraint) {
        return "";
    }

    private String validateGREATER_EQUAL(String constraint) {
        return "";
    }

    private String validateNOT_NULL(String constraint) {
        return "";
    }

    private String validateLIKE(String constraint) {
        return "";
    }

    private String validateBETWEEN(String constraint) {
        return "";
    }

    private String validateIN(String constraint) {
        return "";
    }

    private String validateREGEXP_LIKE(String constraint) {
        return "";
    }

    private String validateNUMBER(String constraint) {
        return "";
    }

    private String validateFLOAT(String constraint) {
        return "";
    }

    private String validateCHAR(String constraint) {
        return "";
    }

    private String validateVARCHAR2(String constraint) {
        return "";
    }

    private String validateDATE(String constraint) {
        return "";
    }

    public static void main(String[] args) {
        new ConstraintChecker();
    }
}
