package DatabaseManagement;

@FunctionalInterface
public interface ValidationFunction {
    public String validate(String constraint, Attribute toValidate,
                           AttributeCollection RestOfAttributes);
}
