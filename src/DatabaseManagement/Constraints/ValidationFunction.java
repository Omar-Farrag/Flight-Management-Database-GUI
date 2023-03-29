package DatabaseManagement.Constraints;

import DatabaseManagement.Tables.Attribute;
import DatabaseManagement.Tables.AttributeCollection;

@FunctionalInterface
public interface ValidationFunction {
    public String validate(String constraint, Attribute toValidate,
                           AttributeCollection RestOfAttributes);
}
