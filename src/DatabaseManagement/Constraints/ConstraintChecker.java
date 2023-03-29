package DatabaseManagement.Constraints;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import DatabaseManagement.DatabaseManager;
import DatabaseManagement.Exceptions.EmptyFileException;
import DatabaseManagement.Tables.*;
import DatabaseManagement.Tables.Attribute.Name;
import DatabaseManagement.Exceptions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.*;

public class ConstraintChecker implements DatabaseManagement.Interfaces.ConstraintChecks {

    private static ConstraintChecker instance;
    private String metaDataFile = "Metadata.json";
    private ArrayList<String> currentApplicationTables;
    private FileWriter fout;
    private ResultSet constraintsTable;
    private ResultSet tableDataTypes;
    private Validator validator;
    private ComparisonCompatibility compChecker;
    private ReferentialResolver resolver;
    private DatabaseManager DB;
    private HashMap<String, JSONObject> table_to_object;

    private ConstraintChecker() {
        currentApplicationTables = new ArrayList<>();
        compChecker = new ComparisonCompatibility();
        resolver = ReferentialResolver.getInstance();
        DB = DatabaseManager.getInstance();

        for (Table t : Table.values())
            currentApplicationTables.add(t.getTableName().toUpperCase());

        validator = new Validator();
        table_to_object = new HashMap<>();

        if (!new File(metaDataFile).exists())
            initMetaDataFile();

        readMetaDataFromFile();
        resolver.initResolver();
    }

    public static ConstraintChecker getInstance() {
        return instance == null ? instance = new ConstraintChecker() : instance;
    }

    public static void main(String[] args) throws IncompatibleFilterException {
        ConstraintChecker.getInstance();
        HashMap<Table, Filters> x =
                ReferentialResolver.getInstance().getReferencingAttributes(Table.USERS,
                        new Attribute(Name.USER_ID, "A7", Table.USERS));

        for (var entry : x.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue().getFilterClause());
        }
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
            if (metaData.isEmpty())
                throw new EmptyFileException(metaDataFile);

            for (Object table : metaData) {
                JSONObject tableJSONObject = (JSONObject) table;
                String tableName = tableJSONObject.get("TableName").toString();

                table_to_object.put(tableName, tableJSONObject);
                JSONObject attributes = (JSONObject) tableJSONObject.get("Attributes");
                for (Object attribute : attributes.keySet()) {
                    JSONArray constraints = (JSONArray) attributes.get(attribute);
                    for (int i = 0; i < constraints.size(); i++) {

                        Table t = Table.valueOf(tableName);
                        Name attName = Name.valueOf(attribute.toString());
                        String constraintName = constraints.get(i).toString();

                        if (constraintName.startsWith("P"))
                            resolver.insertPrimary(t, attName, constraintName.substring(2));
                        else if (constraintName.startsWith("U"))
                            resolver.insertUnique(t, attName, constraintName.substring(2));
                        else if (constraintName.startsWith("R"))
                            resolver.insertForeign(t, attName, constraintName.substring(2));
                    }
                }
            }
            bin.close();
        } catch (IOException |
                 ParseException |
                 EmptyFileException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            initMetaDataFile();
            readMetaDataFromFile();
        }
    }


    @Override
    public Errors checkInsertion(Table t, AttributeCollection toInsert)
            throws DBManagementException {
        checkAttributeExistence(t, toInsert);
        int numAttributes = getTableAttributes(t).size();
        if (numAttributes != toInsert.size())
            throw new InsufficientAttributesException(t, numAttributes, toInsert.size());
        return checkConstraints(t, toInsert);

    }


    @Override
    public Errors checkRetrieval(Filters filters, AttributeCollection toGet)
            throws DBManagementException {

        checkAttributeExistence(toGet);
        AttributeCollection filterCollection = new AttributeCollection(filters);
        checkAttributeExistence(filterCollection);
        return checkConstraints(filterCollection);

    }


    @Override
    public Errors checkRetrieval(Table t, Filters filters) throws DBManagementException {
        AttributeCollection filterCollection = new AttributeCollection(filters);
        checkAttributeExistence(t, filterCollection);
        return checkConstraints(t, filterCollection);
    }


    @Override
    public Errors checkRetrieval(AttributeCollection toGet) throws DBManagementException {

        checkAttributeExistence(toGet);
        return new Errors();
    }


    @Override
    public Errors checkDeletion(Table t, Filters filters) throws SQLException, DBManagementException {
        AttributeCollection filterCollection = new AttributeCollection(filters);
        checkAttributeExistence(t, filterCollection);
        Errors constraintErrors = checkConstraints(t, filterCollection);
        Errors referentialErrors = checkReferencingTables(t, filters);
        return constraintErrors.append(referentialErrors);
    }


    @Override
    public Errors checkUpdate(Table t, Filters filters, AttributeCollection newValues)
            throws SQLException, DBManagementException {

        checkAttributeExistence(t, new AttributeCollection(filters));
        checkAttributeExistence(t, newValues);
        Errors constraintErrorsNew = checkConstraints(t, newValues);
        Errors constraintErrorsFilters = checkConstraints(t, new AttributeCollection(filters));
        Errors referentialErrors = checkReferencingTables(t, filters);

        return constraintErrorsNew.append(constraintErrorsFilters).append(referentialErrors);
    }

    public JSONObject getTableAttributes(Table t) {
        return (JSONObject) table_to_object.get(t.getTableName()).get("Attributes");
    }

    private Errors checkReferencingTables(Table t, Filters f) throws TableNotFoundException, AttributeNotFoundException, SQLException, IncompatibleFilterException, ConstraintNotFoundException {
        Errors errors = new Errors();
        ResultSet toDelete = null;
        try {
            toDelete = DB.retrieve(t, f).getResult();
        } catch (DBManagementException e) {
            throw new RuntimeException(e);
        }
        AttributeCollection referencedAttributes = resolver.getReferencedAttributes(t);

        while (toDelete.next()) {
            for (Attribute attribute : referencedAttributes.attributes()) {
                String toDeleteValue = toDelete.getString(attribute.getStringName());
                Attribute toFindReferences = new Attribute(attribute.getAttributeName(),
                        toDeleteValue, attribute.getT());

                HashMap<Table, Filters> referencingAttributes = resolver.getReferencingAttributes(t, toFindReferences);

                for (Map.Entry<Table, Filters> entry : referencingAttributes.entrySet()) {
                    try {
                        if (DB.retrieve(entry.getKey(), entry.getValue()).getRowsAffected() > 0) {
                            String errorMessage =
                                    "Cannot Delete/Modify Entry With " + attribute.getStringName() +
                                            " = " +
                                            toDeleteValue + " because it is referenced by table " + entry.getKey().getTableName();

                            errors.add(attribute, errorMessage);
                        }
                    } catch (DBManagementException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return errors;
    }

    private Errors checkConstraints(Table t, AttributeCollection toValidate) throws TableNotFoundException, ConstraintNotFoundException {
        JSONObject table = getTableInfoFromMetaData(t);
        JSONObject tableAttributes = (JSONObject) table.get("Attributes");
        Errors errors = new Errors();

        for (Attribute attribute : toValidate.attributes()) {
            JSONArray attributeConstraints = (JSONArray) tableAttributes.get(attribute.getStringName());
            for (Object obj : attributeConstraints) {
                String constraint = (String) obj;
                try {
                    errors.add(attribute, validator.validate(constraint, attribute, toValidate));
                } catch (
                        MissingValidatorException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } catch (DBManagementException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return errors;
    }

    private Errors checkConstraints(AttributeCollection toValidate) throws TableNotFoundException, ConstraintNotFoundException {
        Errors errors = new Errors();

        for (Attribute attribute : toValidate.attributes()) {
            JSONObject table = getTableInfoFromMetaData(attribute.getT());
            JSONObject tableAttributes = (JSONObject) table.get("Attributes");
            JSONArray attributeConstraints = (JSONArray) tableAttributes.get(attribute.getStringName());

            for (Object obj : attributeConstraints) {
                String constraint = (String) obj;
                try {
                    errors.add(attribute, validator.validate(constraint, attribute, toValidate));
                } catch (
                        MissingValidatorException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } catch (DBManagementException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return errors;
    }

    private void checkAttributeExistence(Table t, AttributeCollection toValidate) throws AttributeNotFoundException, TableNotFoundException {
        JSONObject table = getTableInfoFromMetaData(t);
        JSONObject tableAttributes = (JSONObject) table.get("Attributes");

        for (Attribute attribute : toValidate.attributes()) {
            if (!tableAttributes.containsKey(attribute.getStringName()))
                throw new AttributeNotFoundException(t.getTableName(), attribute.getStringName());
        }
    }

    private void checkAttributeExistence(AttributeCollection toGet) throws AttributeNotFoundException, TableNotFoundException {
        for (Attribute attribute : toGet.attributes()) {
            JSONObject table = getTableInfoFromMetaData(attribute.getT());
            JSONObject tableAttributes = (JSONObject) table.get("Attributes");
            if (!tableAttributes.containsKey(attribute.getStringName()))
                throw new AttributeNotFoundException(attribute.getT().getTableName(), attribute.getStringName());
        }
    }

    private JSONObject getTableInfoFromMetaData(Table t) throws TableNotFoundException {
        if (!table_to_object.containsKey(t.getTableName()))
            throw new TableNotFoundException();
        else
            return table_to_object.get(t.getTableName());
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
                            " U.CONSTRAINT_NAME," +
                            " R_CONSTRAINT_NAME" +
                            " FROM USER_CONS_COLUMNS U" +
                            " JOIN ALL_CONSTRAINTS A" +
                            " ON ( U.TABLE_NAME = A.TABLE_NAME" +
                            " AND U.CONSTRAINT_NAME = A.CONSTRAINT_NAME )" +
                            " WHERE U.OWNER = '" + DatabaseManager.getInstance().getUsername() + "'" +
                            " AND A.OWNER = '" + DatabaseManager.getInstance().getUsername() + "'" +
                            " AND U.TABLE_NAME in (" + formattedTableNames + ") ORDER BY CONSTRAINT_TYPE");

            tableDataTypes = DatabaseManager.getInstance().executeStatement(
                    " select *" +
                            " from user_tab_columns" +
                            " order by TABLE_NAME,column_id"

            );

            extractTables(metaData);

            fout.write(metaData.toJSONString());
            fout.flush();

            return true;
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private void extractTables(JSONArray metaData) {

        try {
            DatabaseMetaData meta = DatabaseManager.getInstance().getMetaData();

            for (String tableName : currentApplicationTables) {
                JSONObject table = new JSONObject();
                table.put("TableName", tableName);

                JSONObject attributes = extractAttributesForTable(tableName, meta);

                table.put("Attributes", attributes);
                metaData.add(table);
//                table_to_object.put(tableName, table);
            }
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private JSONObject extractAttributesForTable(String tableName, DatabaseMetaData meta) throws SQLException {
        ResultSet columns = meta.getColumns(null, DatabaseManager.getInstance().getUsername(), tableName, null);
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
        } catch (
                SQLException e) {
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
                else if (constraint.equals("P"))
                    constraint += "_" + constraintsTable.getString("CONSTRAINT_NAME");
                else if (constraint.equals("U"))
                    constraint += "_" + constraintsTable.getString("CONSTRAINT_NAME");


                constraints.add(constraint);
            }
        }
        return constraints;
    }

    public class Errors {
        private final HashMap<Attribute, ArrayList<String>> attribute_to_errors;

        private Errors() {
            attribute_to_errors = new HashMap<>();
        }

        private void add(Attribute attribute, String errorMessage) {
            if (errorMessage.isEmpty()) {
                return;
            }

            if (!attribute_to_errors.containsKey(attribute))
                attribute_to_errors.put(attribute, new ArrayList<String>());

            attribute_to_errors.get(attribute).add(errorMessage);


        }

        public Errors append(Errors error) {
            for (Map.Entry<Attribute, ArrayList<String>> entry : error.attribute_to_errors.entrySet())
                for (String message : entry.getValue())
                    add(entry.getKey(), message);
            return this;
        }

        public boolean noErrors() {
            return attribute_to_errors.isEmpty();
        }

        public ArrayList<String> getErrorByAttribute(Attribute attribute) throws UnvalidatedAttributeException {
            if (!attribute_to_errors.containsKey(attribute))
//                throw new UnvalidatedAttributeException(attribute);
                return new ArrayList<>();
            else
                return attribute_to_errors.get(attribute);
        }

    }
}
