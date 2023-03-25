package DatabaseManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class ComparisonCompatibility {
    private HashMap<Attribute.Type, ArrayList<Filter.FilterType>> type_to_operator;

    public ComparisonCompatibility() {
        type_to_operator = new HashMap<>();

        type_to_operator.put(Attribute.Type.INT, new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
                add(Filter.FilterType.GREATER);
                add(Filter.FilterType.GREATER_EQUAL);
                add(Filter.FilterType.LESS);
                add(Filter.FilterType.LESS_EQUAL);

            }
        });
        type_to_operator.put(Attribute.Type.DOUBLE, new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
                add(Filter.FilterType.GREATER);
                add(Filter.FilterType.GREATER_EQUAL);
                add(Filter.FilterType.LESS);
                add(Filter.FilterType.LESS_EQUAL);
            }
        });
        type_to_operator.put(Attribute.Type.STRING, new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
                add(Filter.FilterType.GREATER);
                add(Filter.FilterType.GREATER_EQUAL);
                add(Filter.FilterType.LESS);
                add(Filter.FilterType.LESS_EQUAL);
            }
        });
        type_to_operator.put(Attribute.Type.DATE, new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
                add(Filter.FilterType.GREATER);
                add(Filter.FilterType.GREATER_EQUAL);
                add(Filter.FilterType.LESS);
                add(Filter.FilterType.LESS_EQUAL);
            }
        });
    }

    public boolean areCompatible(Attribute.Type type, Filter.FilterType operator) {
        if (!type_to_operator.containsKey(type))
            return false;
        return type_to_operator.get(type).indexOf(operator) != -1;
    }
}
