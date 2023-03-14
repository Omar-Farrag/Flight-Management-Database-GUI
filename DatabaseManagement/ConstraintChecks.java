package DatabaseManagement;

import java.util.HashMap;

public interface ConstraintChecks {
    /*
     * Here we can put functions for all the constraints in the database
     * For example, we can have a function for checking uniqueness, a function
     * for checking whether the values are in between two extremes, fuction
     * checking nullity, etc.
     * 
     * This increases the re-usability of input validation functions.
     */

    // TODO: ADD ALL CONSTAINTChecks functions to ConstraintChecks Interface
    public Errors check(Table t, HashMap<String, String> attributes)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException;

}
