package DatabaseManagement;

import java.util.HashMap;
import java.util.Map;

public class Validator {

    private HashMap<Constraint, ValidationFunction> constraint_to_validator;

    public Validator() {
        initConstraintsToValidatorMap();
    }

    public String validate(String constraint, AttributeCollection primaryKey, Attribute toValidate,
            AttributeCollection RestOfAttributes) {
        ValidationFunction validationFunc = find(constraint);
        return validationFunc.validate(constraint, primaryKey, toValidate, RestOfAttributes);
    }

    private ValidationFunction find(String constraint){
        for(Map.Entry<Constraint,ValidationFunction> entry : constraint_to_validator.entrySet()){
            if(entry.getKey().equals(constraint)) return entry.getValue();
        }
        return null;
    }
    // ///////////////////////////////////////////VALIDATORS/////////////////////////////////////////////////

    private String validatePRIMARY(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        System.out.println("Hello From Primary");
        return "";
    }

    private String validateUNIQUE(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        System.out.println("Hellow from Unique");
        return "";
    }

    private String validateFOREIGN(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateLESS_THAN(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateGREATER_THAN(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateEQUAL(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateNOT_EQUAL(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateLESS_EQUAL(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateGREATER_EQUAL(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateNOT_NULL(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateLIKE(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateBETWEEN(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateIN(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateREGEXP_LIKE(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateNUMBER(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateFLOAT(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateCHAR(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateVARCHAR2(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private String validateDATE(String constraint, AttributeCollection PK, Attribute toValidate,
            AttributeCollection remaining) {
        return "";
    }

    private void initConstraintsToValidatorMap() {
        constraint_to_validator = new HashMap<>();

        constraint_to_validator.put(new Constraint(ConstraintEnum.PRIMARY),
                (constraint, PK, toValidate, remaining) -> validatePRIMARY(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.UNIQUE),
                (constraint, PK, toValidate, remaining) -> validateUNIQUE(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.FOREIGN),
                (constraint, PK, toValidate, remaining) -> validateFOREIGN(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.LESS_THAN),
                (constraint, PK, toValidate, remaining) -> validateLESS_THAN(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.GREATER_THAN),
                (constraint, PK, toValidate, remaining) -> validateGREATER_THAN(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.EQUAL),
                (constraint, PK, toValidate, remaining) -> validateEQUAL(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.NOT_EQUAL),
                (constraint, PK, toValidate, remaining) -> validateNOT_EQUAL(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.LESS_EQUAL),
                (constraint, PK, toValidate, remaining) -> validateLESS_EQUAL(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.GREATER_EQUAL),
                (constraint, PK, toValidate, remaining) -> validateGREATER_EQUAL(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.NOT_NULL),
                (constraint, PK, toValidate, remaining) -> validateNOT_NULL(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.LIKE),
                (constraint, PK, toValidate, remaining) -> validateLIKE(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.BETWEEN),
                (constraint, PK, toValidate, remaining) -> validateBETWEEN(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.IN),
                (constraint, PK, toValidate, remaining) -> validateIN(constraint, PK, toValidate, remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.REGEXP_LIKE),
                (constraint, PK, toValidate, remaining) -> validateREGEXP_LIKE(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.NUMBER),
                (constraint, PK, toValidate, remaining) -> validateNUMBER(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.FLOAT),
                (constraint, PK, toValidate, remaining) -> validateFLOAT(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.CHAR),
                (constraint, PK, toValidate, remaining) -> validateCHAR(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.VARCHAR2),
                (constraint, PK, toValidate, remaining) -> validateVARCHAR2(constraint, PK, toValidate,
                        remaining));

        constraint_to_validator.put(new Constraint(ConstraintEnum.DATE),
                (constraint, PK, toValidate, remaining) -> validateDATE(constraint, PK, toValidate,
                        remaining));
    }

    public static void main(String[] args) {
        AttributeCollection collection = new AttributeCollection();
        Attribute att = new Attribute("MALL_NUM", "ABC");
        collection.add(att);

        try {
            ConstraintChecker.getInstance().checkInsertion(Table.MALLS, collection);
        } catch (TableNotFoundException | AttributeNotFoundException | ConstraintNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
