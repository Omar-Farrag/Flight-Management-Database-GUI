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
        } catch (SQLException | ClassNotFoundException e) {
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
    public QueryResult insert(Table t, AttributeCollection toInsert) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, InsufficientAttributesException, SQLException {
        Errors error = ConstraintChecker.getInstance().checkInsertion(t, toInsert);
        String query = "Insert into " + t.getTableName() + "(" + toInsert.getFormattedAttributes() + ") values(" + toInsert.getFormattedValues() + ")";

        return handleDBOperation(t, error, query, true);
    }

    @Override
    public QueryResult delete(Table t, Filter filter) {
        return null;
    }

    @Override
    public QueryResult modify(Table t, Filter filter, AttributeCollection toModify) {
        return null;
    }

    @Override
    public QueryResult retrieve(Table t) throws SQLException {

        String query = "Select * from " + t.getTableName();
        return handleDBOperation(t, null, query, false);

    }

    @Override
    public QueryResult retrieve(Table t, Filter filters) throws IncompatibleFilterException, SQLException,
            TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        Errors error = ConstraintChecker.getInstance().checkRetrieval(t, filters);
        String query = "Select * from " + t.getTableName() + " " + filters.getFilterClause();
        return handleDBOperation(t, error, query, false);
    }

    @Override
    public QueryResult retrieve(Table t, AttributeCollection toGet) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, SQLException {

        Errors error = ConstraintChecker.getInstance().checkRetrieval(t, toGet);
        String query = "Select " + toGet.getFormattedAttributes() + " from " + t.getTableName();
        return handleDBOperation(t, error, query, false);

    }

    @Override
    public QueryResult retrieve(Table t, AttributeCollection toGet, Filter filters) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, IncompatibleFilterException, SQLException {
        Errors error = ConstraintChecker.getInstance().checkRetrieval(t, toGet);
        error.append(ConstraintChecker.getInstance().checkRetrieval(t, filters));

        String query = "Select " + toGet.getFormattedAttributes() + " from " + t.getTableName() + " " + filters.getFilterClause();

        return handleDBOperation(t, error, query, false);
    }

    private QueryResult handleDBOperation(Table t, Errors error, String query, boolean isUpdate) throws SQLException {
        ResultSet rs = null;
        int rows = 0;
        if (error == null || error.noErrors()) {
            if (!isUpdate) {
                rs = executeStatement(query);
                rs.last();
                rows = rs.getRow();
                rs.beforeFirst();
            } else
                rows = executePreparedStatement(query);
        }
        return new QueryResult(rs, rows, error);
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
            AttributeCollection collection = new AttributeCollection();

            Attribute att1 = new Attribute(Attribute.Name.USER_ID, Attribute.Type.STRING, "A11");
            Attribute att2 = new Attribute(Attribute.Name.FNAME, Attribute.Type.STRING, "Ramadan");
            Attribute att3 = new Attribute(Attribute.Name.LNAME, Attribute.Type.STRING, "Kareem");
            Attribute att4 = new Attribute(Attribute.Name.PHONE_NUMBER, Attribute.Type.STRING, "0561234567");
            Attribute att5 = new Attribute(Attribute.Name.EMAIL_ADDRESS, Attribute.Type.STRING, "Ramadan@RealEstate.edu");
            Attribute att6 = new Attribute(Attribute.Name.ROLE_ID, Attribute.Type.STRING, "AC");


            collection.add(att1);
            collection.add(att2);
            collection.add(att3);
            collection.add(att4);
            collection.add(att5);
            collection.add(att6);

            QueryResult res = DB.insert(Table.USERS, collection);

            if (res.noErrors()) {
                System.out.println(res.getRowsAffected());
//                DB.printTable(res.getResult());
            }

            DB.printTable(DB.retrieve(Table.USERS).getResult());


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (ConstraintNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//        catch (AttributeMismatchException e) {
//            System.out.println(e.getMessage());
//        }
        catch (TableNotFoundException e) {
            throw new RuntimeException(e);
        } catch (AttributeNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InsufficientAttributesException e) {
            throw new RuntimeException(e);
        }
//        catch (ConstraintNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

}
