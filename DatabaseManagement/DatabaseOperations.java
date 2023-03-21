package DatabaseManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseOperations {
    // Just a simple iterface for our database manager class

    // Inserts an entry into the database
    public void insert();

    // Deletes a set of entries from the database
    public void delete();

    // Modifies a set of entries in the database
    public void modify();

    // retrieves data from the database
    public ResultSet retrieve(Table t) throws SQLException;

    // retrieves data from the database
    public ResultSet retrieve(Table t, Filter filters) throws IncompatibleFilterException, SQLException,
            TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    // retrieves data from the database
    public ResultSet retrieve(Table t, AttributeCollection toGet);

    // retrieves data from the database
    public ResultSet retrieve(Table t, AttributeCollection toGet, Filter filters);

    // executes a given sql statement
    public ResultSet executeStatement(String sqlStatement) throws SQLException;

    // executes a given sql prepared statement
    public int executePreparedStatement(String sqlPreparedStatement) throws SQLException;
}
