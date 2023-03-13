package DatabaseManagement;

@FunctionalInterface
public interface ValidationFunction {
    public String validate(String constraint);
}
