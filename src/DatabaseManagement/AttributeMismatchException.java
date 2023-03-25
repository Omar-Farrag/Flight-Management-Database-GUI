package DatabaseManagement;

public class AttributeMismatchException extends Exception {
    Attribute attr1;
    Attribute attr2;

    public AttributeMismatchException(Attribute attr1, Attribute attr2) {
        this.attr1 = attr1;
        this.attr2 = attr2;
    }

    @Override
    public String getMessage() {
        return "The attributes " + attr1.getAttributeName() + " and " + attr2.getAttributeName() + " do not match for a 'Between' filtration";
    }
}
