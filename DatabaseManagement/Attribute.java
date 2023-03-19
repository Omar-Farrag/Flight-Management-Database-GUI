package DatabaseManagement;

public class Attribute {
    private Attribute.Name attributeName;
    private String attributeValue;

    public Attribute(Attribute.Name attribute, String value) {
        this.attributeName = attribute;
        this.attributeValue = value;
    }

    public String getAttributeName() {
        return attributeName.getName();
    }

    public int getInt() throws NumberFormatException {
        return Integer.parseInt(attributeValue);
    }

    public double getDouble() throws NumberFormatException {
        return Double.parseDouble(attributeValue);
    }

    public boolean getBoolean() throws NumberFormatException {
        return Boolean.parseBoolean(attributeValue);
    }

    public String getString() {
        return attributeValue;
    }

    public enum Name {
        ELECHARGE("ELECHARGE"),
        WATCONS("WATCONS"),
        ELECONS("ELECONS"),
        WASTEDISPOSED("WASTEDISPOSED"),
        WASTECHARGE("WASTECHARGE"),
        WATCHARGE("WATCHARGE"),
        REQUEST_NUM("REQUEST_NUM"),
        ASSIGNED_TECH("ASSIGNED_TECH"),
        CHARGE("CHARGE"),
        DUE_DATE("DUE_DATE"),
        PAID("PAID"),
        TOTAL_AMOUNT("TOTAL_AMOUNT"),
        UTILITY_ID("UTILITY_ID"),
        PAYER_ID("PAYER_ID"),
        RECEIPT_NUM("RECEIPT_NUM"),
        DATE_PAID("DATE_PAID"),
        UTILITY_PERCENTAGE("UTILITY_PERCENTAGE"),
        MAINTENANCE_PERCENTAGE("MAINTENANCE_PERCENTAGE"),
        LEASE_PERCENTAGE("LEASE_PERCENTAGE"),
        DISCOUNT_NUM("DISCOUNT_NUM"),
        BILL_NUM("BILL_NUM"),
        LEASE_NUM("LEASE_NUM"),
        END_DATE("END_DATE"),
        START_DATE("START_DATE"),
        PAYMENT_OPTION("PAYMENT_OPTION"),
        LEASER_ID("LEASER_ID"),
        STATUS("STATUS"),
        APPLICANT_ID("APPLICANT_ID"),
        LOCATION_NUM("LOCATION_NUM"),
        LEASE_END("LEASE_END"),
        LEASE_START("LEASE_START"),
        LEASE_REQUEST_NUM("LEASE_REQUEST_NUM"),
        AGENT_ID("AGENT_ID"),
        POTENTIAL_TENANT_ID("POTENTIAL_TENANT_ID"),
        SPACE("SPACE"),
        BI_ANNUAL_RATE("BI_ANNUAL_RATE"),
        ANNUAL_RATE("ANNUAL_RATE"),
        MONTHLY_RATE("MONTHLY_RATE"),
        PURPOSE("PURPOSE"),
        QUARTERLY_RATE("QUARTERLY_RATE"),
        CLASS("CLASS"),
        NAME("NAME"),
        MALL_NUM("MALL_NUM"),
        STORE_NUM("STORE_NUM"),
        ADDRESS("ADDRESS"),
        LNAME("LNAME"),
        EMAIL_ADDRESS("EMAIL_ADDRESS"),
        USER_ID("USER_ID"),
        ROLE_ID("ROLE_ID"),
        PHONE_NUMBER("PHONE_NUMBER"),
        FNAME("FNAME"),
        DESCRIPTION("DESCRIPTION"),
        PASSWORD("PASSWORD"),
        MESSAGE("MESSAGE"),
        SENDER_ID("SENDER_ID"),
        RECEIVER_ID("RECEIVER_ID"),
        DATE_SENT("DATE_SENT");

        private String name;

        private Name(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
