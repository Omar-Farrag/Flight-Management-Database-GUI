package DatabaseManagement.Tables;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class AttributeCollection {
    private final Set<Attribute> attributes;

    public AttributeCollection() {
        attributes = new LinkedHashSet<>();
    }

    public AttributeCollection(Filters filters) {
        attributes = new LinkedHashSet<>();
        for (Attribute att : filters.getAttributes())
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

    public String getAliasedFormattedAtt() {
        ArrayList<String> attributes_as_string = new ArrayList<>();

        for (Attribute att : attributes)
            attributes_as_string.add(att.getAliasedStringName());

        return String.join(" , ", attributes_as_string);
    }

    public String getFormattedAtt() {
        ArrayList<String> attributes_as_string = new ArrayList<>();

        for (Attribute att : attributes)
            attributes_as_string.add(att.getStringName());

        return String.join(" , ", attributes_as_string);
    }

    public String getFormattedValues() {
        ArrayList<String> values_as_string = new ArrayList<>();

        for (Attribute att : attributes) {
            values_as_string.add(att.getString());
        }

        return String.join(" , ", values_as_string);
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    public int size() {
        return attributes.size();
    }
}
