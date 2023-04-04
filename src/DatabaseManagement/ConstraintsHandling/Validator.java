package DatabaseManagement.ConstraintsHandling;

import DatabaseManagement.DatabaseManager;
import DatabaseManagement.Exceptions.ConstraintNotFoundException;
import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Exceptions.MissingValidatorException;
import DatabaseManagement.ConstraintsHandling.ReferentialResolver.DetailedKey;
import DatabaseManagement.QueryResult;
import DatabaseManagement.Attribute;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Table;

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
                           AttributeCollection allAttributes) throws DBManagementException {
        ValidationFunction validationFunc = find(constraint);
        return validationFunc.validate(constraint, toValidate, allAttributes);

    }

    private ValidationFunction find(String constraintToSearchFor)
            throws ConstraintNotFoundException, MissingValidatorException {
        for (Constraint constraint : constraints) {
            if (constraint.equals(constraintToSearchFor))
                return constraint.getValidationFunction();
        }
        throw new ConstraintNotFoundException(constraintToSearchFor);
    }

    public String validatePRIMARY(String constraint, Attribute toValidate,
                                  AttributeCollection allAttributes) {
        String errorMessage = validateNOT_NULL(constraint, toValidate, allAttributes);
        if (!errorMessage.isEmpty()) return errorMessage;

        errorMessage = validateUNIQUE(constraint, toValidate, allAttributes);
        if (!errorMessage.isEmpty()) return errorMessage;

        return "";
    }

    public String validateUNIQUE(String constraint, Attribute toValidate,
                                 AttributeCollection allAttributes) {
        try {
            String query = "Select * from " + toValidate.getT().getTableName() +
                    " where " + toValidate.getStringName() + " = " + toValidate.getString();
            ResultSet result = DatabaseManager.getInstance().executeStatement(query);

            if (!result.next()) return "";
            else return toValidate.getValue() + " must be unique...value already used";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String validateFOREIGN(String constraint, Attribute toValidate,
                                  AttributeCollection allAttributes) {

        constraint = constraint.replace("R_", "").trim();
        DetailedKey referenced = ReferentialResolver.getInstance().getReferencedTable(constraint);

        String query =
                "Select * from " + referenced.t.getTableName() + " where " + referenced.column.getName() + " = " + toValidate.getString();
        ResultSet result = null;
        try {
            result = DatabaseManager.getInstance().executeStatement(query);
            if (!result.next()) return "This value is referencing a non-existent entry";
            else return "";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "Something went wrong";
        }
    }

    public String validateLESS_THAN(String constraint, Attribute toValidate,
                                    AttributeCollection allAttributes) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes, "<");

        if (comparisonResult.fieldIsNull || comparisonResult.result == -1) return "";
        else if (comparisonResult.testFailed)
            return "Value you entered could not be compared with" +
                    " stored ranges";
        else
            return comparisonResult.leftOperand + " must be less than " + comparisonResult.rightOperand;
    }

    public String validateGREATER_THAN(String constraint, Attribute toValidate,
                                       AttributeCollection allAttributes) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes, ">");

        if (comparisonResult.fieldIsNull || comparisonResult.result == 1) return "";
        else if (comparisonResult.testFailed)
            return "Value you entered could not be compared with" +
                    " stored ranges";
        else
            return comparisonResult.leftOperand + " must be greater than " + comparisonResult.rightOperand;
    }

    public String validateEQUAL(String constraint, Attribute toValidate,
                                AttributeCollection allAttributes) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes, "=");

        if (comparisonResult.fieldIsNull || comparisonResult.result == 0) return "";
        else if (comparisonResult.testFailed)
            return "Value you entered could not be compared with" +
                    " stored ranges";
        else
            return comparisonResult.leftOperand + " must be equal to " + comparisonResult.rightOperand;
    }

    public String validateNOT_EQUAL(String constraint, Attribute toValidate,
                                    AttributeCollection allAttributes) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes, "!=");

        if (comparisonResult.fieldIsNull || comparisonResult.result != 0) return "";
        else if (comparisonResult.testFailed)
            return "Value you entered could not be compared with" +
                    " stored ranges";
        else
            return comparisonResult.leftOperand + " must not be equal to " + comparisonResult.rightOperand;
    }

    public String validateLESS_EQUAL(String constraint, Attribute toValidate,
                                     AttributeCollection allAttributes) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes, "<=");

        if (comparisonResult.fieldIsNull || comparisonResult.result <= 0) return "";
        else if (comparisonResult.testFailed)
            return "Value you entered could not be compared with" +
                    " stored ranges";
        else
            return comparisonResult.leftOperand + " must be less than or equal to " + comparisonResult.rightOperand;
    }

    public String validateGREATER_EQUAL(String constraint, Attribute toValidate,
                                        AttributeCollection allAttributes) {
        ComparisonResult comparisonResult = compare(constraint, toValidate, allAttributes, ">=");

        if (comparisonResult.fieldIsNull || comparisonResult.result >= 0) return "";
        else if (comparisonResult.testFailed)
            return "Value you entered could not be compared with" +
                    " stored ranges";
        else
            return comparisonResult.leftOperand + " must be greater than or equal to " + comparisonResult.rightOperand;
    }

    private ComparisonResult compare(String constraint, Attribute toValidate,
                                     AttributeCollection allAttributes, String operator) {
        try {

            constraint = constraint.replace("C_", "");
            String[] operands = constraint.split(operator);
            operands[0] = operands[0].trim();
            operands[1] = operands[1].trim();

            Operands resolvedOperands = new Operands(operands, toValidate, allAttributes);
            return new ComparisonResult(operands[0], operands[1], resolvedOperands.compare(),
                    false, false);

        } catch (ParseException | NumberFormatException e) {
            return new ComparisonResult(null, null, -2, false, true);
        } catch (NullPointerException e) {
            return new ComparisonResult(null, null, -2, true, false);

        }
    }

    private Comparable<? extends Comparable<?>> convert(Attribute attribute) throws ParseException, NumberFormatException, NullPointerException {
        switch (attribute.getType()) {
            case DATE -> {
                String dateFormat = "dd-MMM-yyyy";
                SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormat);
                simpleFormat.setLenient(false);
                return simpleFormat.parse(attribute.getValue());
            }
            case NUMBER -> {
                return Double.parseDouble(attribute.getValue());
            }
            default -> {
                return attribute.getValue();
            }
        }


    }

    public String validateNOT_NULL(String constraint, Attribute toValidate,
                                   AttributeCollection allAttributes) {
        if (toValidate.getValue() == null || toValidate.getValue().isEmpty())
            return toValidate.getStringName() + " cannot be null";
        else return "";
    }


    public String validateBETWEEN(String constraint, Attribute toValidate,
                                  AttributeCollection allAttributes) {
        try {
            String[] range =
                    constraint.split("BETWEEN")[1].split("AND");

            range[0] = range[0].trim();
            range[1] = range[1].trim();

            Operands firstOperation = new Operands(new String[]{toValidate.getStringName(), range[0]}, toValidate,
                    allAttributes);

            Operands secondOperation = new Operands(new String[]{toValidate.getStringName(), range[1]}, toValidate,
                    allAttributes);

            int result1 = firstOperation.compare();
            int result2 = secondOperation.compare();

            if (result1 >= 0 && result2 <= 0) return "";

            for (String value : range) {
                value = value.replace("'", "");
                if (value.equals(toValidate.getValue())) return "";
            }
            return toValidate.getStringName() + " must be between " + range[0] + " and " + range[1];
        } catch (ParseException | NumberFormatException e) {
            return "Value entered could not be compared with stored ranges";
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String validateIN(String constraint, Attribute toValidate,
                             AttributeCollection allAttributes) {
        String[] acceptedValues =
                constraint.split("IN")[1].replace("(", "").replace(")", "").trim().split(",");

        for (String value : acceptedValues) {
            value = value.replace("'", "");
            if (value.equals(toValidate.getValue())) return "";
        }
        return toValidate.getStringName() + " must be in one of these values: " + String.join(","
                , acceptedValues);
    }

    public String validateLIKE(String constraint, Attribute toValidate,
                               AttributeCollection allAttributes) {

        String value = toValidate.getValue();
        String pattern = constraint.split("LIKE")[1].trim().replace("'", "");

        if (value == null || value.isEmpty() || regexMatch(value, pattern)) return "";
        else return toValidate.getStringName() + " must be in the following format: " + pattern;
    }

    public String validateREGEXP_LIKE(String constraint, Attribute toValidate,
                                      AttributeCollection allAttributes) {
        String value = toValidate.getValue();
        String pattern = constraint.split(",")[1].trim().replace("'", "").replace(")", "");

        if (value == null || value.isEmpty() || regexMatch(value, pattern)) return "";
        else return toValidate.getStringName() + " must be in the following format: " + pattern;
    }

    private boolean regexMatch(String value, String regex) {
        regex = regex.replace("%", ".*").replace("_", ".");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public String validateNUMBER(String constraint, Attribute toValidate,
                                 AttributeCollection allAttributes) {
        try {
            // tries converting userInput to a BigDecimal
            // throws an exception if it can't
            // An exception means userInput is not a valid NUMBER(precision, scale)
            BigDecimal number = new BigDecimal(toValidate.getValue());

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
            if (toValidate.getValue().isEmpty()) return "";
            else return "Value entered is not a number";
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String validateFLOAT(String constraint, Attribute toValidate,
                                AttributeCollection allAttributes) {
        try {
            Float.parseFloat(toValidate.getValue());
            return "";
        } catch (NumberFormatException e) {
            if (toValidate.getValue().isEmpty()) return "";
            else return "Value entered is not a floating point number";
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String validateCHAR(String constraint, Attribute toValidate,
                               AttributeCollection allAttributes) {
        int length = Integer.parseInt(constraint.split("_")[1]);
        try {
            if (toValidate.getValue().isEmpty()) return "";
            if (toValidate.getValue().length() != length)
                return "Value must have " + length + " characters exactly";
            else return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String validateVARCHAR2(String constraint, Attribute toValidate,
                                   AttributeCollection allAttributes) {
        int maxLength = Integer.parseInt(constraint.split("_")[1]);
        try {
            if (toValidate.getValue().length() > maxLength)
                return "Value too long. A maximum of " + maxLength + " characters is allowed";
            else return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String validateDATE(String constraint, Attribute toValidate,
                               AttributeCollection allAttributes) {
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
            Matcher matcher = pattern.matcher(toValidate.getValue());
            if (!matcher.matches())
                return "Invalid Date Format. Date must be in this format: dd-MMM-yyyy (eg. " +
                        "01-JAN-1970)";

            String dateFormat = "dd-MMM-yyyy";
            SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormat);

            try {
                simpleFormat.setLenient(false);
                // if the date cannot be parsed from userInput, an exception is thrown
                // indicating userInput is invalid Date
                simpleFormat.parse(toValidate.getValue());
                return "";
            } catch (ParseException e) {
                if (toValidate.getValue().isEmpty()) return "";
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
        private boolean fieldIsNull;
        private boolean testFailed;

        public ComparisonResult(String leftOperand, String rightOperand, int result,
                                boolean fieldIsNull, boolean testFailed) {
            this.leftOperand = leftOperand;
            this.rightOperand = rightOperand;
            this.result = result;
            this.fieldIsNull = fieldIsNull;
            this.testFailed = testFailed;
        }
    }

    private class Operands {
        private Comparable lvalue = null;
        private Comparable rvalue = null;


        private Operands(String[] operands,
                         Attribute toValidate, AttributeCollection allAttributes) throws ParseException, NumberFormatException, NullPointerException {
            prepareOperands(operands, toValidate, allAttributes);
        }

        private void prepareOperands(String[] operands,
                                     Attribute toValidate, AttributeCollection allAttributes) throws ParseException, NumberFormatException, NullPointerException {
            for (Attribute attribute : allAttributes.attributes()) {
                String attributeName = attribute.getStringName();
                if (attributeName.equals(operands[0])) {
                    lvalue = convert(attribute);
                } else if (attributeName.equals(operands[1])) {
                    rvalue = convert(attribute);
                }
            }

            if (lvalue == null) lvalue = convert(new Attribute(toValidate.getAttributeName(),
                    operands[0], toValidate.getT()));

            if (rvalue == null) rvalue = convert(new Attribute(toValidate.getAttributeName(),
                    operands[1], toValidate.getT()));
        }

        private int compare() {
            return lvalue.compareTo(rvalue);
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
        AttributeCollection collection = new AttributeCollection();
//
        ConstraintChecker checker = ConstraintChecker.getInstance();
        System.out.println("Done initializing");
//        new Scanner(System.in).nextLine();
//
        try {
            Attribute x1 = new Attribute(Attribute.Name.LEASE_NUM, "L123456789", Table.LEASES);
            Attribute x2 = new Attribute(Attribute.Name.LOCATION_NUM, "", Table.LEASES);
            Attribute x3 = new Attribute(Attribute.Name.END_DATE, "", Table.LEASES);
            Attribute x4 = new Attribute(Attribute.Name.START_DATE, "", Table.LEASES);
            Attribute x5 = new Attribute(Attribute.Name.PAYMENT_OPTION, "", Table.LEASES);
            Attribute x6 = new Attribute(Attribute.Name.LEASER_ID, "", Table.LEASES);


            Attribute x7 = new Attribute(Attribute.Name.MALL_NUM, "M12", Table.LOCS);
            Attribute x8 = new Attribute(Attribute.Name.LOCATION_NUM, "1234567890", Table.LOCS);
            Attribute x9 = new Attribute(Attribute.Name.STORE_NUM, "G20", Table.LOCS);


//            Attribute y = new Attribute(Attribute.Name.ADDRESS, "ABC");
//            // Attribute z = new Attribute(Attribute.Name.STORE_NUM, "ABC");
//
            collection.add(x1);
            collection.add(x2);
            collection.add(x3);
            collection.add(x4);
            collection.add(x5);
            collection.add(x6);

            QueryResult res = DatabaseManager.getInstance().insert(Table.LEASES, collection);
            if (res.noErrors()) {
                System.out.println(res.getRowsAffected());
            } else {
                for (Attribute attribute : collection.attributes())
                    printList(res.getErrors().getErrorByAttribute(attribute));
            }
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

//        String dateTimeString1 = "29-MAR-2023";
//        String dateTimeString2 = "29-MAR-2023";
//
//        String dateFormat = "dd-MMM-yyyy";
//        SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormat);
//        simpleFormat.setLenient(false);
//
//
//        Comparable comp1 = null;
//        Comparable comp2 = null;
//        try {
//            comp1 = simpleFormat.parse(dateTimeString1);
//            comp2 = simpleFormat.parse(dateTimeString2);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(comp1.compareTo(comp2));


//        String value1 = "Go89u";
//        String pattern1 = "C_REGEXP_LIKE(STORE_NUM, '^[GFST]\\w*')";
////        Pattern
//        Validator validator = new Validator();
//        System.out.println(validator.testValidateREGEXP_LIKE(value1, pattern1));

    }

    public static void printList(ArrayList<String> errors) {
        for (String error : errors)
            System.out.println(error);
        System.out.println();
    }
}
