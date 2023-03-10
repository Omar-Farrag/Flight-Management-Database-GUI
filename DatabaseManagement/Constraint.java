package DatabaseManagement;

public enum Constraint {
    // Names of constraints come here.

    CONSTRAINT1("CONSTRAINT 1");

    private String name;

    private Constraint(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void parse() {
        // TODO: implement parse method in Constraint enum
    }

}
