package DatabaseManagement;

public class UnvalidatedAttributeException extends Exception {
    private Attribute attribute;

    public UnvalidatedAttributeException(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getMessage() {
        return "Cannot get error for attribute " + attribute.getAttributeName()
                + ": attribute was not validated in the first place";
    }
}
