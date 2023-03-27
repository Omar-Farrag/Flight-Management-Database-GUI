package DatabaseManagement;

public class InvalidAttributeValueException extends Exception {


    private Attribute attribute;

    public InvalidAttributeValueException(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getMessage() {
        return "The value " + attribute.getString() + " is invalid for attribute " + attribute.getStringName();
    }
}
