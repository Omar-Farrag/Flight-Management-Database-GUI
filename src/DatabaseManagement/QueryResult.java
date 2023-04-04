package DatabaseManagement;

import DatabaseManagement.ConstraintsHandling.ConstraintChecker.Errors;
import DatabaseManagement.Exceptions.DBManagementException;

import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryResult {
    private ResultSet result;
    private int rows;
    private Errors errors;

    public QueryResult(ResultSet result, int rows, Errors errors) {
        this.result = result;
        this.rows = rows;
        this.errors = errors;
    }

    public ResultSet getResult() {
        return result;
    }

    public int getRowsAffected() {
        return rows;
    }

    public Errors getErrors() {
        return errors;
    }

    public boolean noErrors() {
        return errors == null || errors.noErrors();
    }

    public ArrayList<String> getErrorByAttribute(Attribute attribute) throws DBManagementException {
        return errors.getErrorByAttribute(attribute);
    }
}
