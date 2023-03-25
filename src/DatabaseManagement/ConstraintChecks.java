package DatabaseManagement;

import DatabaseManagement.ConstraintChecker.Errors;

public interface ConstraintChecks {
    /*
     * Here we can put functions for all the constraints in the database
     * For example, we can have a function for checking uniqueness, a function
     * for checking whether the values are in between two extremes, fuction
     * checking nullity, etc.
     *
     * This increases the re-usability of input validation functions.
     */

    public Errors checkInsertion(Table t, AttributeCollection toInsert) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkUpdate(Table t, Filter filter, AttributeCollection toUpdate) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkRetrieval(Table t, Filter filter, AttributeCollection toGet) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkRetrieval(Table t, Filter filter) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkRetrieval(Table t, AttributeCollection toGet) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

    public Errors checkDeletion(Table t, Filter filter) throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

}
