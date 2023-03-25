package DatabaseManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Validator {

    private ArrayList<Constraint> constraints;

    public Validator() {
        initConstraintsToValidatorMap();
    }

    public String validate(String constraint, Attribute toValidate,
                           AttributeCollection RestOfAttributes) throws MissingValidatorException, ConstraintNotFoundException {
        ValidationFunction validationFunc = find(constraint);
        return validationFunc.validate(constraint, toValidate, RestOfAttributes);

    }

    private ValidationFunction find(String constraintToSearchFor)
            throws ConstraintNotFoundException, MissingValidatorException {
        for (Constraint constraint : constraints) {
            if (constraint.equals(constraintToSearchFor))
                return constraint.getValidationFunction();
        }
        throw new ConstraintNotFoundException(constraintToSearchFor);
    }
    // ///////////////////////////////////////////VALIDATORS/////////////////////////////////////////////////

    private String validatePRIMARY(String constraint, Attribute toValidate,
                                   AttributeCollection remaining) {
        return "";
    }

    private String validateUNIQUE(String constraint, Attribute toValidate,
                                  AttributeCollection remaining) {
        return "";
    }

    private String validateFOREIGN(String constraint, Attribute toValidate,
                                   AttributeCollection remaining) {
        return "";
    }

    private String validateLESS_THAN(String constraint, Attribute toValidate,
                                     AttributeCollection remaining) {
        return "";
    }

    private String validateGREATER_THAN(String constraint, Attribute toValidate,
                                        AttributeCollection remaining) {
        return "";
    }

    private String validateEQUAL(String constraint, Attribute toValidate,
                                 AttributeCollection remaining) {
        return "";
    }

    private String validateNOT_EQUAL(String constraint, Attribute toValidate,
                                     AttributeCollection remaining) {
        return "";
    }

    private String validateLESS_EQUAL(String constraint, Attribute toValidate,
                                      AttributeCollection remaining) {
        return "";
    }

    private String validateGREATER_EQUAL(String constraint, Attribute toValidate,
                                         AttributeCollection remaining) {
        return "";
    }

    private String validateNOT_NULL(String constraint, Attribute toValidate,
                                    AttributeCollection remaining) {

        return "";
    }

    private String validateLIKE(String constraint, Attribute toValidate,
                                AttributeCollection remaining) {
        return "";
    }

    private String validateBETWEEN(String constraint, Attribute toValidate,
                                   AttributeCollection remaining) {
        return "";
    }

    private String validateIN(String constraint, Attribute toValidate,
                              AttributeCollection remaining) {
        return "";
    }

    private String validateREGEXP_LIKE(String constraint, Attribute toValidate,
                                       AttributeCollection remaining) {

        return "";
    }

    private String validateNUMBER(String constraint, Attribute toValidate,
                                  AttributeCollection remaining) {
        return "";
    }

    private String validateFLOAT(String constraint, Attribute toValidate,
                                 AttributeCollection remaining) {
        return "";
    }

    private String validateCHAR(String constraint, Attribute toValidate,
                                AttributeCollection remaining) {

        return "";
    }

    private String validateVARCHAR2(String constraint, Attribute toValidate,
                                    AttributeCollection remaining) {
        return "";
    }

    private String validateDATE(String constraint, Attribute toValidate,
                                AttributeCollection remaining) {
        return "";
    }

    private void initConstraintsToValidatorMap() {
        constraints = new ArrayList<>();

        constraints.add(new Constraint(ConstraintEnum.PRIMARY, this::validatePRIMARY));
        constraints.add(new Constraint(ConstraintEnum.UNIQUE, this::validateUNIQUE));
        constraints.add(new Constraint(ConstraintEnum.FOREIGN, this::validateFOREIGN));
        constraints.add(new Constraint(ConstraintEnum.LESS_THAN, this::validateLESS_THAN));
        constraints.add(new Constraint(ConstraintEnum.GREATER_THAN, this::validateGREATER_THAN));
        constraints.add(new Constraint(ConstraintEnum.EQUAL, this::validateEQUAL));
        constraints.add(new Constraint(ConstraintEnum.NOT_EQUAL, this::validateNOT_EQUAL));
        constraints.add(new Constraint(ConstraintEnum.LESS_EQUAL, this::validateLESS_EQUAL));
        constraints.add(new Constraint(ConstraintEnum.GREATER_EQUAL, this::validateGREATER_EQUAL));
        constraints.add(new Constraint(ConstraintEnum.NOT_NULL, this::validateNOT_NULL));
        constraints.add(new Constraint(ConstraintEnum.LIKE, this::validateLIKE));
        constraints.add(new Constraint(ConstraintEnum.BETWEEN, this::validateBETWEEN));
        constraints.add(new Constraint(ConstraintEnum.IN, this::validateIN));
        constraints.add(new Constraint(ConstraintEnum.REGEXP_LIKE, this::validateREGEXP_LIKE));
        constraints.add(new Constraint(ConstraintEnum.NUMBER, this::validateNUMBER));
        constraints.add(new Constraint(ConstraintEnum.FLOAT, this::validateFLOAT));
        constraints.add(new Constraint(ConstraintEnum.CHAR, this::validateCHAR));
        constraints.add(new Constraint(ConstraintEnum.VARCHAR2, this::validateVARCHAR2));
        constraints.add(new Constraint(ConstraintEnum.DATE, this::validateDATE));
    }

    public static void main(String[] args) {
        AttributeCollection collection = new AttributeCollection();

        ConstraintChecker checker = ConstraintChecker.getInstance();
        System.out.println("Done initializing");
        new Scanner(System.in).nextLine();

        try {
            Attribute x = new Attribute(Attribute.Name.MALL_NUM, Attribute.Type.INT, "ABC");
            Attribute y = new Attribute(Attribute.Name.ADDRESS, Attribute.Type.STRING, "ABC");
            // Attribute z = new Attribute(Attribute.Name.STORE_NUM, "ABC");

            collection.add(x);
            collection.add(y);
            // collection.add(z);

            ConstraintChecker.Errors errors = checker.checkInsertion(Table.MALLS, collection);
            ArrayList<String> errorsList1 = errors.getErrorByAttribute(x);
            ArrayList<String> errorsList2 = errors.getErrorByAttribute(y);
            // ArrayList<String> errorsList3 = errors.getErrorByAttribute(z);

            printList(errorsList1);
            printList(errorsList2);
            // printList(errorsList3);

        } catch (TableNotFoundException | AttributeNotFoundException | ConstraintNotFoundException |
                 UnvalidatedAttributeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InsufficientAttributesException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printList(ArrayList<String> errors) {
        for (String error : errors)
            System.out.println(error);
        System.out.println();
    }
}
