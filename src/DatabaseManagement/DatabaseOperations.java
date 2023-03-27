package DatabaseManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseOperations {
    // Just a simple iterface for our database manager class

    // Inserts an entry into the database
    public QueryResult insert(Table t, AttributeCollection toInsert) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, InsufficientAttributesException, SQLException;

    // Deletes a set of entries from the database
    public QueryResult delete(Table t, Filters filters) throws IncompatibleFilterException, TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, SQLException;

    // Modifies a set of entries in the database
    public QueryResult modify(Table t, Filters filters, AttributeCollection toModify) throws TableNotFoundException, AttributeNotFoundException, SQLException, IncompatibleFilterException, ConstraintNotFoundException, MissingUpdatedValuesException;


    // retrieves data from the database
    public QueryResult retrieve(Table t) throws SQLException;

    // retrieves data from the database
    public QueryResult retrieve(Table t, Filters filters) throws IncompatibleFilterException, SQLException,
            TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    // retrieves data from the database
    public QueryResult retrieve(Table t, AttributeCollection toGet) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, SQLException;

    // retrieves data from the database
    public QueryResult retrieve(Table t, AttributeCollection toGet, Filters filters) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, IncompatibleFilterException, SQLException;

    // executes a given sql statement
    public ResultSet executeStatement(String sqlStatement) throws SQLException;

    // executes a given sql prepared statement
    public int executePreparedStatement(String sqlPreparedStatement) throws SQLException;
}
