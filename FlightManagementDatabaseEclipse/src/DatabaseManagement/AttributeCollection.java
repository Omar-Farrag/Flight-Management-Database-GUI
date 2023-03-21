package DatabaseManagement;

import java.util.ArrayList;

public class AttributeCollection {
    private ArrayList<Attribute> attributes;

    public AttributeCollection() {
        attributes = new ArrayList<>();
    }

    public void add(Attribute attribute) {
        attributes.add(attribute);
    }

    public ArrayList<Attribute> attributes() {
        return attributes;
    }
}
