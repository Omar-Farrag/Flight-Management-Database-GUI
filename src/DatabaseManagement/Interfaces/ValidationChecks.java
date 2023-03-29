package DatabaseManagement.Interfaces;

import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Tables.Attribute;
import DatabaseManagement.Tables.AttributeCollection;

public interface ValidationChecks {

    String validate(String constraint, Attribute toValidate, AttributeCollection allAttributes) throws DBManagementException;

    String validatePRIMARY(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateUNIQUE(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateFOREIGN(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateLESS_THAN(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateGREATER_THAN(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateEQUAL(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateNOT_EQUAL(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateLESS_EQUAL(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateGREATER_EQUAL(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateNOT_NULL(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateBETWEEN(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateIN(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateLIKE(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateREGEXP_LIKE(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateNUMBER(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateFLOAT(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateCHAR(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateVARCHAR2(String constraint, Attribute toValidate, AttributeCollection allAttributes);

    String validateDATE(String constraint, Attribute toValidate, AttributeCollection allAttributes);
}
