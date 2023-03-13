package DatabaseManagement;

public enum Constraint {
    // Names of constraints come here.

    CONSTRAINT1("CONSTRAINT 1");

    private String name;
    private ConstraintFunctionality functionality;

    private Constraint(String name) {
        this.name = name.toUpperCase();
        functionality = new ConstraintFunctionality();
    }

    public String getName() {
        return name;
    }

    public ConstraintFunctionality get() {
        return functionality;
    }

    private class ConstraintFunctionality {

        @Override
        public boolean equals(Object obj) {
            String constraintString = (String) obj;
            return constraintString.contains(name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

    }

}
