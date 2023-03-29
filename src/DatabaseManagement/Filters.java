package DatabaseManagement;

import java.util.*;

public class Filters {
    private HashMap<Attribute, FilterType> filters;
    private HashMap<Attribute, String[]> filters_IN_Type;
    private ComparisonCompatibility checker;

    public Filters() {
        filters = new HashMap<>();
        filters_IN_Type = new HashMap<>();
        checker = new ComparisonCompatibility();
    }


    public void addGreater(Attribute attribute) {
        filters.put(attribute, FilterType.GREATER);
    }

    public void addGreaterEqual(Attribute attribute) {
        filters.put(attribute, FilterType.GREATER_EQUAL);
    }

    public void addEqual(Attribute attribute) {
        filters.put(attribute, FilterType.EQUAL);
    }

    public void addLessEqual(Attribute attribute) {
        filters.put(attribute, FilterType.LESS_EQUAL);
    }

    public void addLess(Attribute attribute) {
        filters.put(attribute, FilterType.LESS);
    }

    public void addNotEqual(Attribute attribute) {
        filters.put(attribute, FilterType.NOT_EQUAL);
    }

    public void addBetween(Attribute min, Attribute max) throws AttributeMismatchException {
        if (!min.getStringName().equals(max.getStringName()))
            throw new AttributeMismatchException(min, max);
        filters.put(min, FilterType.GREATER_EQUAL);
        filters.put(max, FilterType.LESS_EQUAL);
    }

    public void addIn(Attribute attribute, String[] acceptedValues) {
        filters_IN_Type.put(attribute, acceptedValues);
    }

    public String getFilterClause() throws IncompatibleFilterException {
        if (filters.isEmpty() && filters_IN_Type.isEmpty())
            return "";

        String clause = "where ";
        ArrayList<String> conditions = new ArrayList<>();

        for (Map.Entry<Attribute, FilterType> entry : filters.entrySet()) {
            String condition =
                    entry.getKey().getAliasedStringName() + " " + entry.getValue().getOperator() + " " + entry.getKey().getString();
            conditions.add(condition);
        }

        for (Map.Entry<Attribute, String[]> entry : filters_IN_Type.entrySet()) {
            String condition = entry.getKey().getAliasedStringName() + " IN ";
            ArrayList<String> acceptedInValues = new ArrayList<>();

            if (entry.getKey().getType() == Attribute.Type.STRING)
                for (String value : entry.getValue())
                    acceptedInValues.add("'" + value + "'");
            else
                Collections.addAll(acceptedInValues, entry.getValue());

            condition += "(" + String.join(" , ", acceptedInValues) + ")";
            conditions.add(condition);
        }

        return clause + String.join(" AND ", conditions);
    }


    public Set<Attribute> getAttributes() {
        return filters.keySet();
    }


    public enum FilterType {
        EQUAL("="),
        NOT_EQUAL("!="),

        LESS("<"),
        LESS_EQUAL("<="),

        GREATER(">"),
        GREATER_EQUAL(">="),

        BETWEEN("BETWEEN"),
        IN("IN");

        private String operator;

        private FilterType(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }
    }
}
