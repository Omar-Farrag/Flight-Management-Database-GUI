package DatabaseManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DatabaseManager implements DatabaseOperations {

    private String URL;
    private String username;
    private String password;
    private Connection conn;
    private String constraintsFile = "Metadata.json";
    private ArrayList<String> currentApplicationTables;
    private static DatabaseManager instance;
    private FileWriter fout;
    private JSONArray jsonTables;
    private ResultSet constraintsTable;
    private ResultSet tableDataTypes;

    public static void main(String[] args) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.establishConnection("jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl", "b00087320", "b00087320");
    }

    private DatabaseManager() {
        try {
            fout = new FileWriter(constraintsFile);
            jsonTables = new JSONArray();
            currentApplicationTables = new ArrayList<>();
            for (Table t : Table.values())
                currentApplicationTables.add(t.getTableName().toUpperCase());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    @Override
    public Boolean establishConnection(String URL, String username, String password) {
        try {
            this.URL = URL;
            this.username = username;
            this.password = password;

            conn = DriverManager.getConnection(URL, username, password);

            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String formattedTableNames = "'" + String.join(",", currentApplicationTables).replace(",", "','") + "'";
            constraintsTable = stmt1.executeQuery(
                    " SELECT U.TABLE_NAME," +
                            "U.COLUMN_NAME," +
                            "CONSTRAINT_TYPE," +
                            " SEARCH_CONDITION," +
                            " R_CONSTRAINT_NAME" +
                            " FROM USER_CONS_COLUMNS U" +
                            " JOIN ALL_CONSTRAINTS A" +
                            " ON ( U.TABLE_NAME = A.TABLE_NAME" +
                            " AND U.CONSTRAINT_NAME = A.CONSTRAINT_NAME )" +
                            " WHERE U.OWNER = 'B00087320'" +
                            " AND A.OWNER = 'B00087320'" +
                            " AND U.TABLE_NAME in (" + formattedTableNames + ")");

            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            tableDataTypes = stmt2.executeQuery(
                    " select *" +
                            " from user_tab_columns" +
                            " order by TABLE_NAME,column_id"

            );

            extractTables();

            fout.write(jsonTables.toJSONString());
            fout.flush();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void insert() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void modify() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    @Override
    public void retrieve() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    private void validateConstraints() {
        // tells the constraintChecker class to validate an attibute against given list
        // of constraints
        // TODO: Implement validateConstraints

    }
    // Puts tables and their attributes into a metadata json file
    private void extractTables() {

        try {
            DatabaseMetaData meta = conn.getMetaData();

            for (String tableName : currentApplicationTables) {
                JSONObject table = new JSONObject();
                table.put("TableName", tableName);

                JSONObject attributes = getAttributesForTable(tableName, meta);

                table.put("Attributes", attributes);
                jsonTables.add(table);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }



    private JSONObject getAttributesForTable(String tableName, DatabaseMetaData meta) throws SQLException {
        ResultSet columns = meta.getColumns(null, "B00087320", tableName, null);
        JSONObject attributes = new JSONObject();

        while (columns.next()) {

            String attributeName = columns.getString("COLUMN_NAME");
            JSONArray constraints = new JSONArray();

            ArrayList<String> constraintStrings = getConstraints(tableName, attributeName);
            constraints.add(getDataType(columns.getRow(), tableName));

            for (String constraint : constraintStrings)
                constraints.add(constraint);

            attributes.put(attributeName, constraints);
        }

        return attributes;
    }

    private String getDataType(int rowNumber, String tableName) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            tableDataTypes.beforeFirst();
            while (tableDataTypes.next()) {
                if (tableDataTypes.getString("TABLE_NAME").equals(tableName)) {

                    tableDataTypes.absolute(tableDataTypes.getRow() + rowNumber - 1);
                    String dataType = tableDataTypes.getString("DATA_TYPE");
                    if (dataType.toLowerCase().equals("number"))
                        dataType += getPrecisionAndScale(tableDataTypes);
                    else if (dataType.toLowerCase().equals("varchar2") || dataType.toLowerCase().equals("char"))
                        dataType += getMaxLength(tableDataTypes);
                    return dataType;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";

    }

    private String getMaxLength(ResultSet tableDescription) throws SQLException {
        return "_" + tableDescription.getString("CHAR_COL_DECL_LENGTH");
    }

    private String getPrecisionAndScale(ResultSet tableDescription) throws SQLException {
        return "_" + tableDescription.getString("DATA_PRECISION") + "_" + tableDescription.getString("DATA_SCALE");
    }

    private ArrayList<String> getConstraints(String tableName, String columnName) throws SQLException {

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

}
