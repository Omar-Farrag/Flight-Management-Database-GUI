package DatabaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import DatabaseManagement.ConstraintChecker.Errors;
import DatabaseManagement.Filter.FilterType;

public class DatabaseManager implements DatabaseOperations {

    private String URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private String username = "b00087320";
    private String password = "b00087320";
    private Connection conn;
    private static DatabaseManager instance;

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

    public String getUsername() {
        return username.toUpperCase();
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
    public ResultSet retrieve(Table t) throws SQLException {
        return executeStatement("Select * from " + t.getTableName());
    }

    @Override
    public ResultSet retrieve(Table t, Filter filters) throws IncompatibleFilterException, SQLException,
            TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {
        Errors error;
        if (!(error = ConstraintChecker.getInstance().checkRetrieval(t, null, filters)).noErrors()) {
            System.out.println("error known");
            throw new SQLException();
        }
        String query = "Select * from " + t.getTableName() + " " + filters.getFilterClause();
        return executeStatement(query);
    }

    @Override
    public ResultSet retrieve(Table t, AttributeCollection toGet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieve'");
    }

    @Override
    public ResultSet retrieve(Table t, AttributeCollection toGet, Filter filters) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieve'");
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

    /**
     * Prints the content of a given ResultSet neatly, in tabular form to the
     * console.
     * 
     * @param employees ResultSet to be displayed to the console.
     * @throws SQLException
     */
    public void printTable(ResultSet employees) throws SQLException {
        System.out.println();

        // Printing column headers first
        ResultSetMetaData meta = employees.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.printf("%-22s", meta.getColumnName(i));
        }
        System.out.println();

        // Printing table's tuples
        while (employees.next())
            printRow(employees);
    }

    /**
     * Prints the current row pointed to by the given ResultSet object
     * 
     * @param employees ResultSet pointing to the row to be printed
     * @throws SQLException
     */
    private void printRow(ResultSet employees) throws SQLException {

        for (int i = 1; i <= employees.getMetaData().getColumnCount(); i++) {

            // Special processing for date objects to display only the date without the time
            if (employees.getMetaData().getColumnName(i).equals("HIREDATE")) {
                System.out.printf("%-22s", formatDate(employees, i));
            } else
                System.out.printf("%-22s", employees.getString(i));
        }
        System.out.println();
    }

    /**
     * Formats the date stored at the given row and column. Only the date is shown
     * in dd-mmm-yyyy format without displaying the time
     * 
     * @param row    ResultSet pointing to the row containing the date to be
     *               formatted
     * @param column Column number of the date to be formatted
     * @return Date formatted in 'dd-MMM-yyyy' format
     * @throws SQLException
     */
    private String formatDate(ResultSet row, int column) throws SQLException {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            return dateFormat.format(row.getDate(column));
        } catch (NullPointerException e) {
            return "null";
        }
    }

    public static void main(String[] args) {
        DatabaseManager DB = DatabaseManager.getInstance();
        try {
            Filter filters = new Filter();
            filters.add(new Attribute(Attribute.Name.PAID, Attribute.Type.BOOLEAN, "true"), FilterType.EQUAL);
            // filters.add(new Attribute(Attribute.Name.LNAME, Attribute.Type.STRING,
            // "Farrag"), FilterType.EQUAL);
            DB.printTable(DB.retrieve(Table.BILLS, filters));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IncompatibleFilterException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (TableNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ConstraintNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
