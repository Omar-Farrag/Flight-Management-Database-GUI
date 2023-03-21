package DatabaseManagement;

import java.util.Map;

import DatabaseManagement.Filter.FilterType;

public class IncompatibleFilterException<K, V> extends Exception {

    private Map.Entry<Attribute, FilterType> filter;

    public void Exception(Map.Entry<Attribute, FilterType> entry) {
        this.filter = filter;
    }

    @Override
    public String getMessage() {
        return "The operator " + filter.getValue() + " cannot be used to filter the attribute "
                + filter.getKey().getAttributeName();
    }

}
