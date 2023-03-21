package DatabaseManagement;

import java.util.ArrayList;

public class AttributeCollection {
    private ArrayList<Attribute> attributes;

    public AttributeCollection() {
        attributes = new ArrayList<>();
    }

    public AttributeCollection(Filter filter) {
        attributes = new ArrayList<>();
        for (Attribute att : filter.getAttributes())
            add(attribute);
    }

    public void add(Attribute attribute) {
        attributes.add(attribute);
    }

    public ArrayList<Attribute> attributes() {
        return attributes;
    }
}
