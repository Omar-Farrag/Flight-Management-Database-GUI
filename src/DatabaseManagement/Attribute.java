package DatabaseManagement;

public class Attribute {
    private final Attribute.Name attributeName;
    private final String attributeValue;
    private final Type type;

    public Attribute(Name attributeName, String value) {
        this.attributeName = attributeName;
        this.attributeValue = value;
        this.type = attributeName.type;

    }

    public Attribute(Attribute.Name attribute) {
        this.attributeName = attribute;
        this.attributeValue = "";
        this.type = Type.STRING;

    }

    public String getStringName() {
        return attributeName.getName();
    }

    public Attribute.Name getAttributeName() {
        return attributeName;
    }

    // public int getInt() throws NumberFormatException {
    // return Integer.parseInt(attributeValue);
    // }

    // public double getDouble() throws NumberFormatException {
    // return Double.parseDouble(attributeValue);
    // }

    // public boolean getBoolean() throws NumberFormatException {
    // return Boolean.parseBoolean(attributeValue);
    // }

    public String getString() {
        return attributeValue;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        return attributeName.equals(((Attribute) obj).attributeName);
    }

    @Override
    public int hashCode() {
        return (attributeName.getName()).hashCode();
    }

    public enum Name {
        ELECHARGE("ELECHARGE", Type.NUMBER),
        WATCONS("WATCONS", Type.NUMBER),
        ELECONS("ELECONS", Type.NUMBER),
        WASTEDISPOSED("WASTEDISPOSED", Type.NUMBER),
        WASTECHARGE("WASTECHARGE", Type.NUMBER),
        WATCHARGE("WATCHARGE", Type.NUMBER),
        UTILITY_ID("UTILITY_ID", Type.STRING),
        REQUEST_NUM("REQUEST_NUM", Type.STRING),
        STATUS("STATUS", Type.STRING),
        LEASE_NUM("LEASE_NUM", Type.STRING),
        ASSIGNED_TECH("ASSIGNED_TECH", Type.STRING),
        DESCRIPTION("DESCRIPTION", Type.STRING),
        BILL_NUM("BILL_NUM", Type.STRING),
        CHARGE("CHARGE", Type.NUMBER),
        DUE_DATE("DUE_DATE", Type.DATE),
        PAID("PAID", Type.NUMBER),
        TOTAL_AMOUNT("TOTAL_AMOUNT", Type.NUMBER),
        PAYER_ID("PAYER_ID", Type.STRING),
        RECEIPT_NUM("RECEIPT_NUM", Type.STRING),
        DATE_PAID("DATE_PAID", Type.DATE),
        UTILITY_PERCENTAGE("UTILITY_PERCENTAGE", Type.NUMBER),
        MAINTENANCE_PERCENTAGE("MAINTENANCE_PERCENTAGE", Type.NUMBER),
        LEASE_PERCENTAGE("LEASE_PERCENTAGE", Type.NUMBER),
        DISCOUNT_NUM("DISCOUNT_NUM", Type.STRING),
        LOCATION_NUM("LOCATION_NUM", Type.STRING),
        END_DATE("END_DATE", Type.DATE),
        START_DATE("START_DATE", Type.DATE),
        PAYMENT_OPTION("PAYMENT_OPTION", Type.STRING),
        LEASER_ID("LEASER_ID", Type.STRING),
        APPLICANT_ID("APPLICANT_ID", Type.STRING),
        LEASE_END("LEASE_END", Type.DATE),
        LEASE_START("LEASE_START", Type.DATE),
        LEASE_REQUEST_NUM("LEASE_REQUEST_NUM", Type.STRING),
        AGENT_ID("AGENT_ID", Type.STRING),
        POTENTIAL_TENANT_ID("POTENTIAL_TENANT_ID", Type.STRING),
        SPACE("SPACE", Type.NUMBER),
        BI_ANNUAL_RATE("BI_ANNUAL_RATE", Type.NUMBER),
        ANNUAL_RATE("ANNUAL_RATE", Type.NUMBER),
        MONTHLY_RATE("MONTHLY_RATE", Type.NUMBER),
        PURPOSE("PURPOSE", Type.STRING),
        QUARTERLY_RATE("QUARTERLY_RATE", Type.NUMBER),
        CLASS("CLASS", Type.STRING),
        NAME("NAME", Type.STRING),
        MALL_NUM("MALL_NUM", Type.STRING),
        STORE_NUM("STORE_NUM", Type.STRING),
        ADDRESS("ADDRESS", Type.STRING),
        LNAME("LNAME", Type.STRING),
        EMAIL_ADDRESS("EMAIL_ADDRESS", Type.STRING),
        USER_ID("USER_ID", Type.STRING),
        ROLE_ID("ROLE_ID", Type.STRING),
        PHONE_NUMBER("PHONE_NUMBER", Type.STRING),
        FNAME("FNAME", Type.STRING),
        PASSWORD("PASSWORD", Type.STRING),
        MESSAGE("MESSAGE", Type.STRING),
        SENDER_ID("SENDER_ID", Type.STRING),
        RECEIVER_ID("RECEIVER_ID", Type.STRING),
        DATE_SENT("DATE_SENT", Type.DATE);


        private final String name;
        private final Type type;

        Name(String name, Type type) {
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