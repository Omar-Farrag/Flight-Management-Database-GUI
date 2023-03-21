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
            add(att);
    }

    public AttributeCollection append(AttributeCollection ac) {
        if (ac == null)
            return this;

        for (Attribute attribute : ac.attributes)
            attributes.add(attribute);
        return this;
    }

    public void add(Attribute attribute) {
        attributes.add(attribute);
    }

    public ArrayList<Attribute> attributes() {
        return attributes;
    }
}
