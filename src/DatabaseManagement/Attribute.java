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
        this.attributeValue = value.replace("'", "''");
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

        USERNAME("USERNAME", Type.STRING),
        PASSWORD("PASSWORD", Type.STRING),
        ACCOUNT_TYPE("ACCOUNT_TYPE", Type.STRING),
        CODE("CODE", Type.STRING),
        NAME("NAME", Type.STRING),
        NUMBER("NUMBER", Type.STRING),
        MODEL("MODEL", Type.STRING),
        MAUNFACTURER("MAUNFACTURER", Type.STRING),
        YEAR_BUILT("YEAR_BUILT", Type.DATE),
        AIRLINE_CODE("AIRLINE_CODE", Type.STRING),
        CITY_CODE("CITY_CODE", Type.STRING),
        FLIGHT_FNUMBER("FLIGHT_FNUMBER", Type.STRING),
        PERSON_SSN("PERSON_SSN", Type.NUMBER),
        LOUNGE("LOUNGE", Type.STRING),
        SEAT("SEAT", Type.NUMBER),
        COUNTRY("COUNTRY", Type.STRING),
        SSN("SSN", Type.NUMBER),
        ROLE("ROLE", Type.STRING),
        CREWMEMBER_SSN("CREWMEMBER_SSN", Type.NUMBER),
        FNUMBER("FNUMBER", Type.STRING),
        DURATION("DURATION", Type.DATE),
        FIRST_SEATS("FIRST_SEATS", Type.NUMBER),
        BUSINESS_SEATS("BUSINESS_SEATS", Type.NUMBER),
        ECONOMY("ECONOMY", Type.NUMBER),
        DISTANCE("DISTANCE", Type.NUMBER),
        STATUS("STATUS", Type.STRING),
        DEPARTURE("DEPARTURE", Type.STRING),
        ARRIVAL("ARRIVAL", Type.STRING),
        GATES_GNUMBER("GATES_GNUMBER", Type.NUMBER),
        AIRPLANE_NUMBER("AIRPLANE_NUMBER", Type.STRING),
        AIRPORT_INCOMING_CODE("AIRPORT_INCOMING_CODE", Type.STRING),
        AIRPORT_OUTCOMING_CODE2("AIRPORT_OUTCOMING_CODE2", Type.STRING),
        GNUMBER("GNUMBER", Type.NUMBER),
        FLOOR("FLOOR", Type.STRING),
        LOCATION("LOCATION", Type.STRING),
        AIRPORT_CODE("AIRPORT_CODE", Type.STRING),
        FNAME("FNAME", Type.STRING),
        LNAME("LNAME", Type.STRING),
        AGE("AGE", Type.NUMBER),
        PERRSON_SSN("PERRSON_SSN", Type.NUMBER),
        PRICE("PRICE", Type.NUMBER),
        PASSENGER_SSN("PASSENGER_SSN", Type.NUMBER),
        ROWS_AFFECTED("ROWS_AFFECTED", Type.STRING),
        TABLE("TABLE", Type.STRING),
        ACTIVITY("ACTIVITY", Type.STRING),
        DATE("DATE", Type.DATE),
        ACCOUNT_USERNAME("ACCOUNT_USERNAME", Type.STRING);

        private final String attName;
        private final Type type;

        Name(String name, Type type) {
            this.attName = name;
            this.type = type;
        }

        public String getName() {
            return attName;
        }
    }

    public enum Type {
        NUMBER, STRING, DATE;
    }
}
