package DatabaseManagement;

import DatabaseManagement.ConstraintsHandling.ConstraintChecker.Errors;
import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Interfaces.QueryResultInterace;

import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryResult implements QueryResultInterace {
    private ResultSet result;
    private int rows;
    private Errors errors;

    public QueryResult(ResultSet result, int rows, Errors errors) {
        this.result = result;
        this.rows = rows;
        this.errors = errors;
    }

    @Override
    public ResultSet getResult() {
        return result;
    }

    @Override
    public int getRowsAffected() {
        return rows;
    }

    @Override
    public Errors getErrors() {
        return errors;
    }

    @Override
    public boolean noErrors() {
        return errors == null || errors.noErrors();
    }

    @Override
    public ArrayList<String> getErrorByAttribute(Attribute attribute) throws DBManagementException {
        return errors.getErrorByAttribute(attribute);
    }
}
