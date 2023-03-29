package DatabaseManagement;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DatabaseManagement.ConstraintChecker.Errors;
import DatabaseManagement.QueryGeneration.InvalidJoinException;
import DatabaseManagement.QueryGeneration.QueryGenerator;

public class DatabaseManager implements DatabaseOperations {

    private String URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private String username = "b00087320";
    private String password = "b00087320";
    private Connection conn;
    private static DatabaseManager instance;
    private ReferentialResolver resolver;


    private DatabaseManager() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, username, password);
            resolver = ReferentialResolver.getInstance();
        } catch (
                SQLException |
                ClassNotFoundException e) {
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

    public DatabaseMetaData getMetaData() throws SQLException {
        return conn.getMetaData();
    }

    @Override
    public QueryResult insert(Table t, AttributeCollection toInsert) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, InsufficientAttributesException, SQLException {
        Errors error = ConstraintChecker.getInstance().checkInsertion(t, toInsert);
        String query = "Insert into " + t.getTableName() + "(" + toInsert.getFormattedAtt() + ") " +
                "values(" + toInsert.getFormattedValues() + ")";

        return handleDBOperation(error, query, true);
    }

    @Override
    public QueryResult delete(Table t, Filters filters) throws IncompatibleFilterException, TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, SQLException {
        Errors error = ConstraintChecker.getInstance().checkDeletion(t, filters);
        String query = "Delete From " + t.getTableName() + " " + filters.getFilterClause();


        return handleDBOperation(error, query, true);
    }

    @Override
    public QueryResult modify(Table t, Filters filters, AttributeCollection toModify) throws TableNotFoundException, AttributeNotFoundException, SQLException, IncompatibleFilterException, ConstraintNotFoundException, MissingUpdatedValuesException {

        if (toModify.isEmpty()) throw new MissingUpdatedValuesException(t);

        Errors error = ConstraintChecker.getInstance().checkUpdate(t, filters, toModify);
        String query =
                "Update " + t.getTableName() + " " + getModificationClause(toModify) + " " + filters.getFilterClause();

        return handleDBOperation(error, query, true);


    }

    private String getModificationClause(AttributeCollection toModify) {
        String clause = "set ";
        ArrayList<String> updates = new ArrayList<>();
        for (Attribute attribute : toModify.attributes()) {
            String update = attribute.getStringName() + " = " + attribute.getString();
            updates.add(update);
        }
        return clause + String.join(",", updates);

    }

    @Override
    public QueryResult retrieve(Table t) throws SQLException {

        String query = "Select * from " + t.getAliasedName();
        return handleDBOperation(null, query, false);

    }

    @Override
    public QueryResult retrieve(Table t, Filters filters) throws IncompatibleFilterException, SQLException,
            TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        Errors error = ConstraintChecker.getInstance().checkRetrieval(t, filters);
        String query = "Select * from " + t.getAliasedName() + " " + filters.getFilterClause();
        return handleDBOperation(error, query, false);
    }

    @Override
    public QueryResult retrieve(AttributeCollection toGet) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, SQLException, InvalidJoinException {

        QueryGenerator generator = new QueryGenerator(toGet);
        Errors error = ConstraintChecker.getInstance().checkRetrieval(toGet);
        String query = "Select " + toGet.getAliasedFormattedAtt() + " from " + generator.getFromClause();
        return handleDBOperation(error, query, false);

    }

    @Override
    public QueryResult retrieve(AttributeCollection toGet, Filters filters) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, IncompatibleFilterException, SQLException, InvalidJoinException {
        Errors error = ConstraintChecker.getInstance().checkRetrieval(toGet);
        error.append(ConstraintChecker.getInstance().checkRetrieval(new AttributeCollection(filters)));

        QueryGenerator generator = new QueryGenerator(toGet);
        String query =
                "Select " + toGet.getAliasedFormattedAtt() + " from " + generator.getFromClause() + " " + filters.getFilterClause();

        return handleDBOperation(error, query, false);
    }

    private QueryResult handleDBOperation(Errors error, String query, boolean isUpdate) throws SQLException {
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

        System.out.println(sqlStatement);
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
        } catch (
                NullPointerException e) {
            return "null";
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        DatabaseManager DB = DatabaseManager.getInstance();
        long endTime = System.currentTimeMillis();

        System.out.println("Established Connection in " + ((endTime - startTime) / 1000.0) + " seconds");
        try {
            AttributeCollection collection = new AttributeCollection();

            Attribute x1 = new Attribute(Attribute.Name.UTILITY_PERCENTAGE, "99",
                    Table.DISCOUNTS);
            Attribute x2 = new Attribute(Attribute.Name.MAINTENANCE_PERCENTAGE, "23",
                    Table.DISCOUNTS);
            Attribute x3 = new Attribute(Attribute.Name.LEASE_PERCENTAGE, "34", Table.DISCOUNTS);
            Attribute x4 = new Attribute(Attribute.Name.DISCOUNT_NUM, "D222456789",
                    Table.DISCOUNTS);
            Attribute x5 = new Attribute(Attribute.Name.BILL_NUM, "B133456789",
                    Table.DISCOUNTS);


            Attribute y1 = new Attribute(Attribute.Name.BILL_NUM, "B123456789", Table.BILLS);
            Attribute y2 = new Attribute(Attribute.Name.TOTAL_AMOUNT, "5000", Table.BILLS);
            Attribute y3 = new Attribute(Attribute.Name.UTILITY_ID, "U236567891", Table.BILLS);
            Attribute y4 = new Attribute(Attribute.Name.LEASE_NUM, "L123456789", Table.BILLS);
            Attribute y5 = new Attribute(Attribute.Name.DUE_DATE, "22-MAR-2024", Table.BILLS);
            Attribute y6 = new Attribute(Attribute.Name.PAID, "0", Table.BILLS);


            collection.add(x1);
            collection.add(x2);
            collection.add(x3);
            collection.add(x4);
            collection.add(x5);

//            collection.add(x7);
//            collection.add(x8);
//            collection.add(x9);


            QueryResult res = DB.insert(Table.DISCOUNTS, collection);
//            QueryResult res = DB.retrieve(Table.BILLS);
//
            if (res.noErrors()) {
                System.out.println(res.getRowsAffected());
                DB.printTable(DB.retrieve(Table.DISCOUNTS).getResult());
                System.out.println();
                DB.printTable(DB.retrieve(Table.BILLS).getResult());
                System.out.println();
                DB.printTable(DB.retrieve(Table.LEASES).getResult());
                System.out.println();
                DB.printTable(DB.retrieve(Table.UTILITY_CONSUMPTION).getResult());
//                DB.printTable(res.getResult());
            } else {
                for (Attribute attribute : collection.attributes()) {
                    for (String error : res.getErrors().getErrorByAttribute(attribute)) {
                        System.out.println(error);
                    }
                }
            }

//            startTime = System.currentTimeMillis();
//
//            ConstraintChecker.getInstance();
//            endTime = System.currentTimeMillis();
//
//            System.out.println("Initialized in " + ((endTime - startTime) / 1000.0) + " seconds");
//


        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

}
