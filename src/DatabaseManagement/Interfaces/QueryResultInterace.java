package DatabaseManagement.Interfaces;

import DatabaseManagement.Constraints.ConstraintChecker;
import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Tables.Attribute;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface QueryResultInterace {
    ResultSet getResult();

    int getRowsAffected();

    ConstraintChecker.Errors getErrors();

    boolean noErrors();

    ArrayList<String> getErrorByAttribute(Attribute attribute) throws DBManagementException;
}
