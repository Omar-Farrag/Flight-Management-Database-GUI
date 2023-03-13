package DatabaseManagement;

public class Constraint {

    private ConstraintEnum constraintEnum;

    public Constraint(ConstraintEnum constraintEnum) {
        this.constraintEnum = constraintEnum;
    }

    @Override
    public boolean equals(Object obj) {
        String constraintString = (String) obj;
        return constraintString.contains(constraintEnum.getName());
    }

    @Override
    public int hashCode() {
        return constraintEnum.getName().hashCode();
    }

}
