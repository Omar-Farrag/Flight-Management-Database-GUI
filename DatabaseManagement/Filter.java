package DatabaseManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Filter {
    private HashMap<Attribute, FilterType> filters;

    public Filter() {
        filters = new HashMap<>();
    }

    public void add(Attribute attribute, FilterType type) {
        filters.put(attribute, type);
    }

    public String getFilterClause() {
        if (filters.isEmpty())
            return "";
        String clause = "where ";
        ArrayList<String> conditions;

        for (Map.Entry<Attribute, FilterType> entry : filters.entrySet()) {
            String condition = entry.getKey().getAttributeName() + " " + entry.getValue().getName();
            conditions.add();
        }
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

        public String getName() {
            return name;
        }
    }
}
