package DatabaseManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Filter {
    private HashMap<Attribute, FilterType> filters;
    private ComparisonCompatibility checker;

    public Filter() {
        filters = new HashMap<>();
        checker = new ComparisonCompatibility();
    }

    public void add(Attribute attribute, FilterType type) {
        filters.put(attribute, type);
    }

    public String getFilterClause() throws IncompatibleFilterException {
        if (filters.isEmpty())
            return "";
        String clause = "where ";
        ArrayList<String> conditions = new ArrayList<>();

        for (Map.Entry<Attribute, FilterType> entry : filters.entrySet()) {
            if (checkCompatibility(entry)) {
                String condition = entry.getKey().getAttributeName() + " " + entry.getValue().getOperator();
                if (entry.getKey().getType() == Attribute.Type.STRING)
                    condition += " '" + entry.getKey().getString() + "'";
                else
                    condition += " " + entry.getKey().getString();
                conditions.add(condition);
            }

        }
        return clause + String.join(" AND ", conditions);
    }

    public Set<Attribute> getAttributes() {
        return filters.keySet();
    }

    public boolean checkCompatibility(Map.Entry<Attribute, FilterType> filter) throws IncompatibleFilterException {
        if (!checker.areCompatible(filter.getKey().getType(), filter.getValue()))
            throw new IncompatibleFilterException(filter);
        return true;
    }

    public enum FilterType {
        EQUAL("="),
        NOT_EQUAL("!="),

        LESS("<"),
        LESS_EQUAL("<="),

        GREATER(">"),
        GREATER_EQUAL(">=");

        private String operator;

        private FilterType(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }
    }
}
