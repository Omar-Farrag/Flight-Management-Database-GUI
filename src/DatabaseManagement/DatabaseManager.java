package DatabaseManagement;

import java.sql.*;

import DatabaseManagement.ConstraintsHandling.ConstraintChecker;
import DatabaseManagement.ConstraintsHandling.ConstraintChecker.Errors;
import DatabaseManagement.Exceptions.*;
import DatabaseManagement.QueryGeneration.QueryGenerator;

public class DatabaseManager {

    private final String URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private final String username = "b00087320";
    private final String password = "b00087320";
    private Connection conn;
    private static DatabaseManager instance;


    private DatabaseManager() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, username, password);
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

    /**
     * Inserts the given attribute collection to the given table. The attributes in the collection must match the
     * attributes in the table, but the order is irrelevant. If you want to set one of the attributes in the table to
     * null, then set the value of that attribute in the attribute collection to null, pass an empty string to the
     * attribute's value.
     *
     * @param toInsert list of attributes forming the tuple to be inserted
     * @param t        Table where the tuple will be inserted
     * @return The result of the insertion operation
     * @throws SQLException          If an error occurs while inserting the data into the DB.
     * @throws DBManagementException If the attributes in toInsert do not match the attributes in table T
     */
    public QueryResult insert(Table t, AttributeCollection toInsert) throws SQLException, DBManagementException {
        Errors error = null;
        try {
            error = ConstraintChecker.getInstance().checkInsertion(t, toInsert);
        } catch (DBManagementException e) {
            throw new RuntimeException(e);
        }
        String query = "Insert into " + t.getTableName() + "(" + toInsert.getFormattedAtt() + ") " +
                "values(" + toInsert.getFormattedValues() + ")";

        return handleDBOperation(error, query, true);
    }


    /**
     * Deletes rows in the given table that satisfy all the given filters. Passing an empty filters object will
     * delete the whole table.
     *
     * @param t       Table whose entries are to be deleted.
     * @param filters Conditions that a row must satisfy to be deleted
     * @return Query result of the delete operation
     * @throws SQLException          If an error occurs while data the data from the DB.
     * @throws DBManagementException Print the message to know why the exception was thrown
     */
    public QueryResult delete(Table t, Filters filters) throws SQLException, DBManagementException {
        Errors error = null;
        try {
            error = ConstraintChecker.getInstance().checkDeletion(t, filters);
        } catch (DBManagementException e) {
            throw new RuntimeException(e);
        }
        String query = "Delete From " + t.getTableName() + " " + filters.getFilterClause();


        return handleDBOperation(error, query, true);
    }

    /**
     * Updates the values of certain rows in the given table
     *
     * @param t        Table whose rows are to be modified
     * @param filters  Conditions that a row must satisfy to be updatedd
     * @param toModify Attribute collection containing the attributes to be modified and their new values
     * @return Result of modification operation
     * @throws SQLException          If an error occurs while updating the data in the DB.
     * @throws DBManagementException Print the message to know why the exception was thrown
     */
    public QueryResult modify(Table t, Filters filters, AttributeCollection toModify) throws SQLException, DBManagementException {

        if (toModify.isEmpty()) throw new MissingUpdatedValuesException(t);

        Errors error = null;
        try {
            error = ConstraintChecker.getInstance().checkUpdate(t, filters, toModify);
        } catch (DBManagementException e) {
            throw new RuntimeException(e);
        }
        String query =
                "Update " + t.getTableName() + " " + QueryGenerator.getSetClause(toModify) + " " + filters.getFilterClause();

        return handleDBOperation(error, query, true);

    }

    /**
     * Retrieves all rows from a specific table
     *
     * @param t Table whose rows are to be retrieved
     * @return QueryResult containing the result set of the retrieved table
     * @throws SQLException If an error occurs while retrieving the data from the DB.
     */
    public QueryResult retrieve(Table t) throws SQLException {

        String query = "Select * from " + t.getAliasedName();
        return handleDBOperation(null, query, false);

    }

    /**
     * Retrieves specific rows from a given table
     *
     * @param t       Table containing the rows to be retrieved
     * @param filters Conditions that a row must satisfy to be part of the retrieved set of rows
     * @return QueryResult containing a result set of the retrieved rows
     * @throws SQLException          If an error occurs while retrieving the data from the DB.
     * @throws DBManagementException Print the message to know why the exception was thrown
     */
    public QueryResult retrieve(Table t, Filters filters) throws SQLException, DBManagementException {

        Errors error = null;
        try {
            error = ConstraintChecker.getInstance().checkRetrieval(t, filters);
        } catch (DBManagementException e) {
            throw new RuntimeException(e);
        }
        String query = "Select * from " + t.getAliasedName() + " " + filters.getFilterClause();
        return handleDBOperation(error, query, false);
    }

    /**
     * Joins the tables containing the attributes in the given attribute collection and retrieves all their
     * rows. Only the attributes in the collection are selected from the rows. Bear in mind that the tables
     * containing the attributes must be eligible for joining, otherwise an exception is thrown.
     *
     * @param toGet Collection of attributes to be retrieved
     * @return QueryResult containing the result set of the retrieval operation
     * @throws SQLException          If an error occurs while retrieving the data from the DB.
     * @throws DBManagementException Print the message to know why the exception was thrown
     */
    public QueryResult retrieve(AttributeCollection toGet) throws SQLException, DBManagementException {

        Errors error = null;
        try {
            error = ConstraintChecker.getInstance().checkRetrieval(toGet);
        } catch (DBManagementException e) {
            throw new RuntimeException(e);
        }
        String query =
                "Select " + toGet.getAliasedFormattedAtt() + " from " + new QueryGenerator(toGet).getFromClause();
        return handleDBOperation(error, query, false);

    }

    /**
     * Joins the tables containing the attributes in the given attribute collection and filters then retrieves rows
     * that satisfy certain conditions. Only the attributes in the collection are selected from the rows. Bear in
     * mind that the tables containing the attributes in the attribute collection and filters must be eligible for
     * joining, otherwise an exception is thrown.
     *
     * @param toGet   Attributes to be retrieved
     * @param filters Conditions that a row must satisfy to be part of the retrieved set of rows
     * @return QueryResult containing the result set of the retrieval operation
     * @throws SQLException          If an error occurs while retrieving the data from the DB.
     * @throws DBManagementException Print the message to know why the exception was thrown
     */
    public QueryResult retrieve(AttributeCollection toGet, Filters filters) throws SQLException, DBManagementException {
        Errors error = ConstraintChecker.getInstance().checkRetrieval(filters, toGet);

        QueryGenerator generator = new QueryGenerator(toGet, filters);
        String query =
                "Select " + toGet.getAliasedFormattedAtt() + " from " + generator.getFromClause() + " " + filters.getFilterClause();

        return handleDBOperation(error, query, false);
    }


    /**
     * Executes the given SQL statement. Only Select SQL statements allowed
     *
     * @param sqlStatement Statement to be executed
     * @return Result set of the executed statement
     * @throws SQLException If an error occurs while executing the SQL statement in the DBMS
     */
    public ResultSet executeStatement(String sqlStatement) throws SQLException {

        System.out.println(sqlStatement);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt.executeQuery(sqlStatement);
    }

    /**
     * Executes the given SQL statement. Only Insert, Update, & Delete SQL statements are allowed
     *
     * @param sqlPreparedStatement Statement to be executed
     * @return Number of rows affected by the SQL operation
     * @throws SQLException If an error occurs while executing SQL Prepared Statement in the DBMS.
     */
    public int executePreparedStatement(String sqlPreparedStatement) throws SQLException {
        PreparedStatement prep = conn.prepareStatement(sqlPreparedStatement);
        return prep.executeUpdate();
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

//    /**
//     * Prints the content of a given ResultSet neatly, in tabular form to the
//     * console.
//     *
//     * @param employees ResultSet to be displayed to the console.
//     * @throws SQLException
//     */
//      @Override
//    public void printTable(ResultSet employees) throws SQLException {
//        System.out.println();
//
//        // Printing column headers first
//        ResultSetMetaData meta = employees.getMetaData();
//        for (int i = 1; i <= meta.getColumnCount(); i++) {
//            System.out.printf("%-22s", meta.getColumnName(i));
//        }
//        System.out.println();
//
//        // Printing table's tuples
//        while (employees.next())
//            printRow(employees);
//    }
//
//    /**
//     * Prints the current row pointed to by the given ResultSet object
//     *
//     * @param employees ResultSet pointing to the row to be printed
//     * @throws SQLException
//     */
//    private void printRow(ResultSet employees) throws SQLException {
//
//        for (int i = 1; i <= employees.getMetaData().getColumnCount(); i++) {
//
//            // Special processing for date objects to display only the date without the time
//            if (employees.getMetaData().getColumnName(i).equals("HIREDATE")) {
//                System.out.printf("%-22s", formatDate(employees, i));
//            } else
//                System.out.printf("%-22s", employees.getString(i));
//        }
//        System.out.println();
//    }
//
//    /**
//     * Formats the date stored at the given row and column. Only the date is shown
//     * in dd-mmm-yyyy format without displaying the time
//     *
//     * @param row    ResultSet pointing to the row containing the date to be
//     *               formatted
//     * @param column Column number of the date to be formatted
//     * @return Date formatted in 'dd-MMM-yyyy' format
//     * @throws SQLException
//     */
//    private String formatDate(ResultSet row, int column) throws SQLException {
//        try {
//            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//            return dateFormat.format(row.getDate(column));
//        } catch (
//                NullPointerException e) {
//            return "null";
//        }
//    }
//
//    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        DatabaseManager DB = DatabaseManager.getInstance();
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("Established Connection in " + ((endTime - startTime) / 1000.0) + " seconds");
//        try {
//            AttributeCollection collection = new AttributeCollection();
//
//            Attribute x1 = new Attribute(Attribute.Name.UTILITY_PERCENTAGE, "99",
//                    Table.DISCOUNTS);
//            Attribute x2 = new Attribute(Attribute.Name.MAINTENANCE_PERCENTAGE, "23",
//                    Table.DISCOUNTS);
//            Attribute x3 = new Attribute(Attribute.Name.LEASE_PERCENTAGE, "34", Table.DISCOUNTS);
//            Attribute x4 = new Attribute(Attribute.Name.DISCOUNT_NUM, "D222456789",
//                    Table.DISCOUNTS);
//            Attribute x5 = new Attribute(Attribute.Name.BILL_NUM, "B133456789",
//                    Table.DISCOUNTS);
//
//
//            Attribute y1 = new Attribute(Attribute.Name.BILL_NUM, "B123456789", Table.BILLS);
//            Attribute y2 = new Attribute(Attribute.Name.TOTAL_AMOUNT, "5000", Table.BILLS);
//            Attribute y3 = new Attribute(Attribute.Name.UTILITY_ID, "U236567891", Table.BILLS);
//            Attribute y4 = new Attribute(Attribute.Name.LEASE_NUM, "L123456789", Table.BILLS);
//            Attribute y5 = new Attribute(Attribute.Name.DUE_DATE, "22-MAR-2024", Table.BILLS);
//            Attribute y6 = new Attribute(Attribute.Name.PAID, "0", Table.BILLS);
//
//
//            collection.add(x1);
//            collection.add(x2);
//            collection.add(x3);
//            collection.add(x4);
//            collection.add(x5);
//
////            collection.add(x7);
////            collection.add(x8);
////            collection.add(x9);
//
//
////            QueryResult res = DB.insert(Table.DISCOUNTS, collection);
//            QueryResult res = DB.retrieve(Table.BILLS);
////
//            if (res.noErrors()) {
//                System.out.println(res.getRowsAffected());
//                DB.printTable(DB.retrieve(Table.DISCOUNTS).getResult());
//                System.out.println();
//                DB.printTable(DB.retrieve(Table.BILLS).getResult());
//                System.out.println();
//                DB.printTable(DB.retrieve(Table.LEASES).getResult());
//                System.out.println();
//                DB.printTable(DB.retrieve(Table.UTILITY_CONSUMPTION).getResult());
////                DB.printTable(res.getResult());
//            } else {
//                for (Attribute attribute : collection.attributes()) {
//                    for (String error : res.getErrors().getErrorByAttribute(attribute)) {
//                        System.out.println(error);
//                    }
//                }
//            }
//
////            startTime = System.currentTimeMillis();
////
////            ConstraintChecker.getInstance();
////            endTime = System.currentTimeMillis();
////
////            System.out.println("Initialized in " + ((endTime - startTime) / 1000.0) + " seconds");
////
//
//
//        } catch (
//                Exception e) {
//            e.printStackTrace();
//        }
//    }

}
