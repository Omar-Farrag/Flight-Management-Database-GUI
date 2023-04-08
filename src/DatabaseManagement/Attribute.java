package DatabaseManagement;

public class Attribute {

    private final Name attributeName;
    private final String attributeValue;
    private final Type type;
    private final Table t;

    /**
     * Creates an instance of the attribute object. Use this constructor for
     * operations where the value of the attribute is needed, such as performing
     * Update, Insert, filtering, etc.
     *
     * @param attributeName Name of the Attribute as written in the database
     * @param value Value of the attribute as a string
     * @param t The table that the attribute belongs to
     */
    public Attribute(Name attributeName, String value, Table t) {
        this.attributeName = attributeName;
        this.attributeValue = value;
        this.type = attributeName.type;
        this.t = t;

    }

    /**
     * Creates an instance of the attribute object. Use this constructor for
     * operations where the value of the attribute is not needed such as when
     * retrieving specific attributes from a table.
     *
     * @param attribute Name of the attribute as written in the database
     * @param t The table that the attribute belongs to
     */
    public Attribute(Name attribute, Table t) {
        this.attributeName = attribute;
        this.attributeValue = "";
        this.type = Type.STRING;
        this.t = t;

    }

    public String getStringName() {
        return attributeName.getName();
    }

    public Name getAttributeName() {
        return attributeName;
    }

    public String getAliasedStringName() {
        return t.getAlias() + "." + getStringName();
    }

    public String getValue() {
        return attributeValue;
    }

    public String getStringValue() {
        if (type.equals(Type.STRING) || type.equals((Type.DATE))) {
            return "'" + attributeValue + "'";
        } else if (attributeValue == null) {
            return "NULL";
        } else {
            return attributeValue;
        }

    }

    public Type getType() {
        return type;
    }

    public Table getT() {
        return t;
    }

    @Override
    public boolean equals(Object obj) {
        Attribute other = (Attribute) obj;
        return attributeName.equals(other.attributeName) && t.equals(other.t);
    }

    @Override
    public int hashCode() {
        return (attributeName.getName()).hashCode();
    }

    /**
     * Known problem: if two attributes have the same name but different types,
     * bad things will happen Solution: make the user specify the type in the
     * Attribute Constructor. Moreover, in the validator class, in the
     * validation functions for the data types, add an extra step where the type
     * is checked to make sure that it is correct. For example in the validation
     * function, for VARCHAR2, check that the type of attribute is STRING. If it
     * is not, throw an exception
     * <p>
     * <p>
     * In our case, no two attributes have the same name and different types so
     * we're good
     */
    public enum Name {

        private final String name;
        private final Type type;

        Name(String
        name, Type
        type


            ) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }
    }

    public enum Type {
        NUMBER, STRING, DATE;
    }

    public static void main(String[] args) {
        String x = "3.23";
        System.out.println(Integer.parseInt(x));
    }
}
