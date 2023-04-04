package DatabaseManagement.Interfaces;

import DatabaseManagement.ConstraintsHandling.ConstraintChecker.Errors;
import DatabaseManagement.Exceptions.*;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Filters;
import DatabaseManagement.Table;

import java.sql.SQLException;

public interface ConstraintChecks {
    Errors checkInsertion(Table t, AttributeCollection toInsert) throws DBManagementException;

    Errors checkRetrieval(Filters filters, AttributeCollection toGet) throws DBManagementException;

    Errors checkRetrieval(Table t, Filters filters) throws DBManagementException;

    Errors checkRetrieval(AttributeCollection toGet) throws DBManagementException;

    Errors checkDeletion(Table t, Filters filters) throws SQLException, DBManagementException;

    Errors checkUpdate(Table t, Filters filters, AttributeCollection newValues)
            throws SQLException, DBManagementException;
}
