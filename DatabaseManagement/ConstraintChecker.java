package DatabaseManagement;

import java.util.ArrayList;
import java.util.HashMap;


public class ConstraintChecker implements ConstraintChecks {

    // We can store key value pairs of the constraint and its corresponding checker
    // function
    private HashMap<Constraint,ValidationFunction> constraint_to_validator;

    

    public ConstraintChecker() {
        constraint_to_validator = new HashMap<>();
    }

    // Checks a given attribute against a given list of constraints
    public void check(String tableName, ArrayList<String> attributes) {
        // TODO implement Check functiofn in ConstraintChecker
        // Retrieve the table object from json file
        // For each attribute get the list of constraints
        // For each constraint, send it to the Constraint Enum for parsing to get the corresponding CONSTRAINT ENUM
        // Look in the hashmap for the corresponding validation function
        // Execute validation function
    }

    private void getTableInfoFromMetaData(String tableName) {
        // Gets the list attributes and their constrainst from metadata.jsonfile
        // TODO: Implement findConstraints

    }

}
