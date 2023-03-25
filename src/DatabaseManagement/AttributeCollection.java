package DatabaseManagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AttributeCollection {
    private final Set<Attribute> attributes;

    public AttributeCollection() {
        attributes = new HashSet<>();
    }

    public AttributeCollection(Filter filter) {
        attributes = new HashSet<>();
        for (Attribute att : filter.getAttributes())
            add(att);
    }

    public AttributeCollection append(AttributeCollection ac) {
        if (ac != null)
            attributes.addAll(ac.attributes);

        return this;
    }

    public void add(Attribute attribute) {
        attributes.add(attribute);
    }

    public Set<Attribute> attributes() {
        return attributes;
    }

    public String getFormattedAttributes() {
        ArrayList<String> attributes_as_string = new ArrayList<>();

        for (Attribute att : attributes)
            attributes_as_string.add(att.getAttributeName());

        return String.join(" , ", attributes_as_string);
    }

    public String getFormattedValues() {
        ArrayList<String> values_as_string = new ArrayList<>();

        for (Attribute att : attributes) {
            if (att.getType().equals(Attribute.Type.STRING))
                values_as_string.add("'" + att.getString() + "'");
            else values_as_string.add(att.getString());
        }

        return String.join(" , ", values_as_string);
    }

    public int size() {
        return attributes.size();
    }
}
