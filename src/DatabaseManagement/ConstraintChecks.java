package DatabaseManagement;

import DatabaseManagement.ConstraintChecker.Errors;

import java.sql.SQLException;

public interface ConstraintChecks {
    /*
     * Here we can put functions for all the constraints in the database
     * For example, we can have a function for checking uniqueness, a function
     * for checking whether the values are in between two extremes, fuction
     * checking nullity, etc.
     *
     * This increases the re-usability of input validation functions.
     */

    public Errors checkInsertion(Table t, AttributeCollection toInsert) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, InsufficientAttributesException;

    public Errors checkUpdate(Table t, Filters filters, AttributeCollection toUpdate) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, SQLException, IncompatibleFilterException;

    public Errors checkRetrieval(Table t, Filters filters, AttributeCollection toGet) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkRetrieval(Table t, Filters filters) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkRetrieval(Table t, AttributeCollection toGet) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkDeletion(Table t, Filters filters) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException, SQLException, IncompatibleFilterException;

}
