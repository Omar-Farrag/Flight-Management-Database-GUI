package DatabaseManagement;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private ArrayList<Constraint> constraints;

    public Validator() {
        initConstraintsToValidatorMap();
    }

    public String validate(String constraint, Attribute toValidate,
                           AttributeCollection allAttributes, Table t) throws MissingValidatorException,
            ConstraintNotFoundException {
        ValidationFunction validationFunc = find(constraint);
        return validationFunc.validate(constraint, toValidate, allAttributes, t);

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
                                   AttributeCollection allAttributes, Table t) {
        String errorMessage = validateNOT_NULL(constraint, toValidate, allAttributes, t);
        if (!errorMessage.isEmpty()) return errorMessage;

        errorMessage = validateUNIQUE(constraint, toValidate, allAttributes, t);
        if (!errorMessage.isEmpty()) return errorMessage;

        return "";
    }

    private String validateUNIQUE(String constraint, Attribute toValidate,
                                  AttributeCollection allAttributes, Table t) {
        try {
            String query = "Select * from " + t.getTableName() +
                    " where " + toValidate.getStringName() + " = ";
            if (toValidate.getType() == Attribute.Type.STRING)
                query += "'" + toValidate.getString() + "'";
            else query += toValidate.getString();
            ResultSet result = DatabaseManager.getInstance().executeStatement(query);

            if (!result.next()) return "";
            else return toValidate.getString() + " must be unique...value already used";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String validateFOREIGN(String constraint, Attribute toValidate,
                                   AttributeCollection allAttributes, Table t) {
        return "";
    }

    private String validateLESS_THAN(String constraint, Attribute toValidate,
                                     AttributeCollection allAttributes, Table t) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes);
        if (comparisonResult.testFailed || comparisonResult.result == -1) return "";
        else
            return comparisonResult.leftOperand + " must be less than " + comparisonResult.rightOperand;
    }

    private String validateGREATER_THAN(String constraint, Attribute toValidate,
                                        AttributeCollection allAttributes, Table t) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes);
        if (comparisonResult.testFailed || comparisonResult.result == 1) return "";
        else
            return comparisonResult.leftOperand + " must be greater than " + comparisonResult.rightOperand;
    }

    private String validateEQUAL(String constraint, Attribute toValidate,
                                 AttributeCollection allAttributes, Table t) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes);
        if (comparisonResult.testFailed || comparisonResult.result == 0) return "";
        else
            return comparisonResult.leftOperand + " must be equal to " + comparisonResult.rightOperand;
    }

    private String validateNOT_EQUAL(String constraint, Attribute toValidate,
                                     AttributeCollection allAttributes, Table t) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes);
        if (comparisonResult.testFailed || comparisonResult.result != 0) return "";
        else
            return comparisonResult.leftOperand + " must not be equal to " + comparisonResult.rightOperand;
    }

    private String validateLESS_EQUAL(String constraint, Attribute toValidate,
                                      AttributeCollection allAttributes, Table t) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes);
        if (comparisonResult.testFailed || comparisonResult.result <= 0) return "";
        else
            return comparisonResult.leftOperand + " must be less than or equal to " + comparisonResult.rightOperand;
    }

    private String validateGREATER_EQUAL(String constraint, Attribute toValidate,
                                         AttributeCollection allAttributes, Table t) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes);
        if (comparisonResult.testFailed || comparisonResult.result >= 0) return "";
        else
            return comparisonResult.leftOperand + " must be greater than or equal to " + comparisonResult.rightOperand;
    }

    private ComparisonResult compare(String constraint, Attribute toValidate,
                                     AttributeCollection allAttributes) {
        try {

            Comparable lvalue = null;
            Comparable rvalue = null;

            constraint = constraint.replace("C_", "");
            String[] operands = constraint.split(">=");
            operands[0] = operands[0].trim();
            operands[1] = operands[1].trim();

            //look for operand in list of remaining attributes;
            for (Attribute attribute : allAttributes.attributes()) {
                String attributeName = attribute.getStringName();
                if (attributeName.equals(operands[0])) {
                    lvalue = convert(attribute);
                } else if (attributeName.equals(operands[1])) {
                    rvalue = convert(attribute);
                }
            }

            if (lvalue == null) lvalue = convert(new Attribute(toValidate.getAttributeName(),
                    operands[0]));

            if (rvalue == null) rvalue = convert(new Attribute(toValidate.getAttributeName(),
                    operands[1]));

            return new ComparisonResult(operands[0], operands[1], lvalue.compareTo(rvalue), false);
        } catch (ParseException | NumberFormatException | NullPointerException e) {
            return new ComparisonResult(null, null, -2, true);
        }
    }

    private Comparable<? extends Comparable<?>> convert(Attribute attribute) throws ParseException, NumberFormatException, NullPointerException {
        switch (attribute.getType()) {
            case DATE -> {
                String dateFormat = "dd-MMM-yyyy";
                SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormat);
                simpleFormat.setLenient(false);
                return simpleFormat.parse(attribute.getString());
            }
            case NUMBER -> {
                return Double.parseDouble(attribute.getString());
            }
            default -> {
                return attribute.getString();
            }
        }


    }

    private String validateNOT_NULL(String constraint, Attribute toValidate,
                                    AttributeCollection allAttributes, Table t) {
        if (toValidate.getString() == null || toValidate.getString().isEmpty())
            return toValidate.getStringName() + " cannot be null";
        else return "";
    }

    private String validateLIKE(String constraint, Attribute toValidate,
                                AttributeCollection allAttributes, Table t) {
        return "";
    }

    private String validateBETWEEN(String constraint, Attribute toValidate,
                                   AttributeCollection allAttributes, Table t) {
        return "";
    }

    private String validateIN(String constraint, Attribute toValidate,
                              AttributeCollection allAttributes, Table t) {
        return "";
    }

    private String validateREGEXP_LIKE(String constraint, Attribute toValidate,
                                       AttributeCollection allAttributes, Table t) {

        return "";
    }

    private String validateNUMBER(String constraint, Attribute toValidate,
                                  AttributeCollection allAttributes, Table t) {
        try {
            // tries converting userInput to a BigDecimal
            // throws an exception if it can't
            // An exception means userInput is not a valid NUMBER(precision, scale)
            BigDecimal number = new BigDecimal(toValidate.getString());

            String[] decomposedConstraint = constraint.split("_");

            int precision = Integer.parseInt(decomposedConstraint[1]);
            int scale = Integer.parseInt(decomposedConstraint[2]);

            if (number.scale() > scale)
                return "Number must not have more than " + scale + " digits after the decimal " +
                        "point";

            // If number's precision is less than the maximum, yet its precision
            // prevents it from having the required scale, userInput is invalid
            // Example : precision = 7, scale = 2, number = 123456
            // number's precision is 6. However, adding two digits after the decimal point
            // (number = 123456.00) to achieve a scale of 2 results in number's precision
            // becoming 8, which exceeds the maximum allowed precision of 7
            if (number.precision() + scale > precision)
                return "Number must not have more than " + (precision - scale) + " digits before " +
                        "the decimal point";

            if (number.precision() > precision)
                return "Number must not exceed " + precision + " digits";

            return "";
        } catch (NumberFormatException e) {
            if (toValidate.getString().isEmpty()) return "";
            else return "Value entered is not a number";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String validateFLOAT(String constraint, Attribute toValidate,
                                 AttributeCollection allAttributes, Table t) {
        try {
            Float.parseFloat(toValidate.getString());
            return "";
        } catch (NumberFormatException e) {
            if (toValidate.getString().isEmpty()) return "";
            else return "Value entered is not a floating point number";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String validateCHAR(String constraint, Attribute toValidate,
                                AttributeCollection allAttributes, Table t) {
        int length = Integer.parseInt(constraint.split("_")[1]);
        try {
            if (toValidate.getString().isEmpty()) return "";
            if (toValidate.getString().length() != length)
                return "Value must have " + length + " characters exactly";
            else return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String validateVARCHAR2(String constraint, Attribute toValidate,
                                    AttributeCollection allAttributes, Table t) {
        int maxLength = Integer.parseInt(constraint.split("_")[1]);
        try {
            if (toValidate.getString().length() > maxLength)
                return "Value too long. A maximum of " + maxLength + " characters is allowed";
            else return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String validateDATE(String constraint, Attribute toValidate,
                                AttributeCollection allAttributes, Table t) {
        // The main logic of this function, is to match the userInput with the
        // dateFormat specified below. If SimpleDateFormat object can parse a date with
        // the specified dateFormat from the userInput, then the userInput is a valid
        // DATE

        // For some reason, the above fails to recognize a date like '15-feb-202222' as
        // invalid. Therefore, as an extra check before performing the above, regex
        // matching is done to ensure that the format of the userInput is precisely
        // dd-mon-yyyy;
        try {
            String regex = "\\d{1,2}-.{3}-\\d{1,4}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(toValidate.getString());
            if (!matcher.matches())
                return "Invalid Date Format. Date must be in this format: dd-MMM-yyyy (eg. " +
                        "01-JAN-1970)";

            String dateFormat = "dd-MMM-yyyy";
            SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormat);

            try {
                simpleFormat.setLenient(false);
                // if the date cannot be parsed from userInput, an exception is thrown
                // indicating userInput is invalid Date
                simpleFormat.parse(toValidate.getString());
                return "";
            } catch (ParseException e) {
                if (toValidate.getString().isEmpty()) return "";
                else return "Invalid Date Format. Date must be in this format: dd-MMM-yyyy (eg. " +
                        "01-JAN-1970)";
            }
        } catch (NullPointerException e) {
            return "";
        }
    }

    private class ComparisonResult {
        private String leftOperand;
        private String rightOperand;
        private int result;
        private boolean testFailed;

        public ComparisonResult(String leftOperand, String rightOperand, int result, boolean testFailed) {
            this.leftOperand = leftOperand;
            this.rightOperand = rightOperand;
            this.result = result;
            this.testFailed = testFailed;
        }
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
//        AttributeCollection collection = new AttributeCollection();
//
//        ConstraintChecker checker = ConstraintChecker.getInstance();
//        System.out.println("Done initializing");
//        new Scanner(System.in).nextLine();
//
//        try {
//            Attribute x = new Attribute(Attribute.Name.MALL_NUM, "ABC");
//            Attribute y = new Attribute(Attribute.Name.ADDRESS, "ABC");
//            // Attribute z = new Attribute(Attribute.Name.STORE_NUM, "ABC");
//
//            collection.add(x);
//            collection.add(y);
//            // collection.add(z);
//
//            ConstraintChecker.Errors errors = checker.checkInsertion(Table.MALLS, collection);
//            ArrayList<String> errorsList1 = errors.getErrorByAttribute(x);
//            ArrayList<String> errorsList2 = errors.getErrorByAttribute(y);
//            // ArrayList<String> errorsList3 = errors.getErrorByAttribute(z);
//
//            printList(errorsList1);
//            printList(errorsList2);
//            // printList(errorsList3);
//
//        } catch (TableNotFoundException | AttributeNotFoundException | ConstraintNotFoundException |
//                 UnvalidatedAttributeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        } catch (InsufficientAttributesException e) {
//            throw new RuntimeException(e);
//        }

        String dateTimeString1 = "29-MAR-2023";
        String dateTimeString2 = "29-MAR-2023";

        String dateFormat = "dd-MMM-yyyy";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormat);
        simpleFormat.setLenient(false);


        Comparable comp1 = null;
        Comparable comp2 = null;
        try {
            comp1 = simpleFormat.parse(dateTimeString1);
            comp2 = simpleFormat.parse(dateTimeString2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println(comp1.compareTo(comp2));

    }

    public static void printList(ArrayList<String> errors) {
        for (String error : errors)
            System.out.println(error);
        System.out.println();
    }
}
