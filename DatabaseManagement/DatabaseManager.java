package DatabaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DatabaseManager implements DatabaseOperations {

    private String URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private String username = "b00087320";
    private String password = "b00087320";
    private Connection conn;
    private static DatabaseManager instance;

    public static void main(String[] args) {
        DatabaseManager db = DatabaseManager.getInstance();

    }

    private DatabaseManager() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    public Connection getConn() {
        return conn;
    }
    public String getUsername(){
        return username;
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

    @Override
    public ResultSet executeStatement(String sqlStatement) throws SQLException {

        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt.executeQuery(sqlStatement);
    }

    @Override
    public int executePreparedStatement(String sqlPreparedStatement) throws SQLException {
        PreparedStatement prep = conn.prepareStatement(sqlPreparedStatement);
        return prep.executeUpdate();
    }

}
