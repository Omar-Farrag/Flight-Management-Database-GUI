package DatabaseManagement.Interfaces;

import DatabaseManagement.ConstraintsHandling.ConstraintChecker;
import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Attribute;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface QueryResultInterace {
    ResultSet getResult();

    int getRowsAffected();

    ConstraintChecker.Errors getErrors();

    boolean noErrors();

    ArrayList<String> getErrorByAttribute(Attribute attribute) throws DBManagementException;
}
