package DatabaseManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class ComparisonCompatibility {
    private HashMap<String, ArrayList<Filter.FilterType>> type_to_operator;

    public ComparisonCompatibility() {
        type_to_operator = new HashMap<>();

        type_to_operator.put("CHAR", new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
            }
        });
        type_to_operator.put("VARCHAR2", new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
            }
        });
        type_to_operator.put("NUMBER", new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
                add(Filter.FilterType.GREATER);
                add(Filter.FilterType.GREATER_EQUAL);
                add(Filter.FilterType.LESS);
                add(Filter.FilterType.LESS_EQUAL);
            }
        });
        type_to_operator.put("FLOAT", new ArrayList<Filter.FilterType>() {
            {
                add(Filter.FilterType.EQUAL);
                add(Filter.FilterType.NOT_EQUAL);
                add(Filter.FilterType.GREATER);
                add(Filter.FilterType.GREATER_EQUAL);
                add(Filter.FilterType.LESS);
                add(Filter.FilterType.LESS_EQUAL);
            }
        });
        type_to_operator.put("DATE", new ArrayList<Filter.FilterType>() {
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

    public boolean areCompatible(String dataType, Filter.FilterType operator ){
        if(!type_to_operator.containsKey(dataType)) return false;
        return type_to_operator.get(dataType).indexOf(operator) != -1;
    }
    public static void main(String[] args) {
        ComparisonCompatibility cc = new ComparisonCompatibility();
        System.out.println(cc.areCompatible("CHAR",Filter.))
    }
}
