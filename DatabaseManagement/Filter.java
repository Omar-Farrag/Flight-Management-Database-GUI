package DatabaseManagement;

import java.util.HashMap;

public class Filter {
    private HashMap<Attribute, FilterType> filters;

    public Filter() {
        filters = new HashMap<>();
    }

    public void add(Attribute attribute, FilterType type) {
        filters.put(attribute, type);
    }

    public String getFilterClause() {
        Attribute
    }

    public enum FilterType {
        EQUAL("="),
        NOT_EQUAL("!="),

        LESS("<"),
        LESS_EQUAL("<="),

        GREATER(">"),
        GREATER_EQUAL(">=");

        private String name;

        private FilterType(String name) {
            this.name = name;
        }
    }
}
