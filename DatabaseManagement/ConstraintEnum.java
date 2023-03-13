package DatabaseManagement;

public enum ConstraintEnum {
    // Names of constraints come here.

    PRIMARY(""),
    UNIQUE(""),
    LESS_THAN("<")
    GREATER_THAN(">")
    EQUAL("=")
    NOT_EQUAL("!=")
    LESS_EQUAL("<=")
    GREATER_EQUAL(">=")
    NOT_NULL("IS NOT NULL")
    LIKE("LIKE")
    BETWEEN("BETWEEN")
    IN("IN")
    REGEXP_LIKE("REGEXP_LIKE")
    NUMBER("NUMBER")
    FLOAT("FLOAT")
    CHAR("CHAR")
    VARCHAR2("VARCHAR2")
    DATE("DATE")


    private String name;

    private ConstraintEnum(String name) {
        this.name = name.toUpperCase();
    }

    public String getName() {
        return name;
    }

}
