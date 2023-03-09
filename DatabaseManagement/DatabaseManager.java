package DatabaseManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static void main(String[] args) {
        DatabaseManager db = DatabaseManager.getInstance();
        db.establishConnection("jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl", "b00087320", "b00087320");
    }

    private DatabaseManager() {
        try {
            currentApplicationTables = new ArrayList<>();
            for (Table t : Table.values())
                currentApplicationTables.add(t.getTableName().toLowerCase());

            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    /**
     * 
     * This function is used mainly to initialize the URL, username, and password of
     * the database account.
     * 
     * @param URL:     String URL of the database to establish connection with
     * @param username Username of the user who's account will be used to login to
     *                 database
     * @param password Password of the user's account.
     * @return Boolean : True if the connection was established successfully and all
     *         relevant data was extracted from Database Catalog
     */
    @Override
    public Boolean establishConnection(String URL, String username, String password) {
        try {
            this.URL = URL;
            this.username = username;
            this.password = password;
            conn = DriverManager.getConnection(URL, username, password);
            extractTables();
            populateDataTypes();
            populateConstraints();
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
            FileWriter fout = new FileWriter(constraintsFile);

            ResultSet tables = meta.getTables(null, "B00087320", null, new String[] { "TABLE" });
            JSONArray jsonTables = new JSONArray();

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                if (currentApplicationTables.contains(tableName.toLowerCase())) {

                    JSONObject table = new JSONObject();
                    table.put("TableName", tableName);

                    JSONObject attributes = getAttributesForTable(tableName, meta);

                    table.put("Attributes", attributes);
                    jsonTables.add(table);
                }
            }
            fout.write(jsonTables.toJSONString());
            fout.flush();
            conn.close();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private JSONObject getAttributesForTable(String tableName, DatabaseMetaData meta) throws SQLException {
        ResultSet columns = meta.getColumns(null, "B00087320", tableName, null);
        JSONObject attributes = new JSONObject();

        while (columns.next())
            attributes.put(columns.getString("COLUMN_NAME"), new JSONArray());
        return attributes;
    }

    private void populateDataTypes() {
        // Adds the datatypes to the metadata.jsonfile.
        // TODO: Implement ExtractTables
    }

    private void populateConstraints() {
        // Reads the constraints json file and enters the constraints appropriately in
        // metadata.json
        // TODO: Implement populateConstraints

    }

    private void validateConstraints() {
        // tells the constraintChecker class to validate an attibute against given list
        // of constraints
        // TODO: Implement validateConstraints

    }

}
