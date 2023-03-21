package DatabaseManagement;

@FunctionalInterface
public interface ValidationFunction {
    public String validate(String constraint, AttributeCollection primaryKey, Attribute toValidate,
            AttributeCollection RestOfAttributes);
}
