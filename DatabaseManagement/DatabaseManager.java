package DatabaseManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public static void main(String[] args) {
        try {

            DatabaseManager db = DatabaseManager.getInstance();
            db.establishConnection("jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl", "b00087320", "b00087320");

        } catch (Exception e) {
            e.printStackTrace();
        }

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

            extractTables();
            populateConstraints();

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


    // Puts tables and their attributes into a metadata json file
    private void extractTables() {

        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, "B00087320", null, new String[] { "TABLE" });

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                if (currentApplicationTables.contains(tableName.toUpperCase())) {

                    JSONObject table = new JSONObject();
                    table.put("TableName", tableName);

                    JSONObject attributes = getAttributesForTable(tableName, meta);

                    table.put("Attributes", attributes);
                    jsonTables.add(table);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void validateConstraints() {
        // tells the constraintChecker class to validate an attibute against given list
        // of constraints
        // TODO: Implement validateConstraints

    }

    private JSONObject getAttributesForTable(String tableName, DatabaseMetaData meta) throws SQLException {
        ResultSet columns = meta.getColumns(null, "B00087320", tableName, null);
        JSONObject attributes = new JSONObject();

        while (columns.next()) {

            String attributeName = columns.getString("COLUMN_NAME");
            JSONArray constraints = new JSONArray();
            constraints.add(getDataType(attributeName));
            attributes.put(attributeName, constraints);
        }

        return attributes;
    }

    private String getDataType(String attributeName) {
        try {
            Statement stmt = conn.createStatement();
            for (String table : currentApplicationTables) {
                ResultSet tableDescription = stmt.executeQuery(
                        " select *" +
                                " from user_tab_columns" +
                                " where table_name = '" + table +
                                "' order by column_id"

                );
                while (tableDescription.next()) {
                    if (tableDescription.getString("COLUMN_NAME").equals(attributeName)) {
                        String dataType = tableDescription.getString("DATA_TYPE");
                        if (dataType.toLowerCase().equals("number"))
                            dataType += getPrecisionAndScale(tableDescription);
                        else if (dataType.toLowerCase().equals("varchar2") || dataType.toLowerCase().equals("char"))
                            dataType += getMaxLength(tableDescription);
                        return dataType;
                    }
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

    private void populateConstraints() {

    }


}
