package DatabaseManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class ComparisonCompatibility {
    private HashMap<Attribute.Type, ArrayList<Filters.FilterType>> type_to_operator;

//    public ComparisonCompatibility() {
//        type_to_operator = new HashMap<>();
//
//        type_to_operator.put(Attribute.Type.INT, new ArrayList<Filters.FilterType>() {
//            {
//                add(Filters.FilterType.EQUAL);
//                add(Filters.FilterType.NOT_EQUAL);
//                add(Filters.FilterType.GREATER);
//                add(Filters.FilterType.GREATER_EQUAL);
//                add(Filters.FilterType.LESS);
//                add(Filters.FilterType.LESS_EQUAL);
//
//            }
//        });
//        type_to_operator.put(Attribute.Type.DOUBLE, new ArrayList<Filters.FilterType>() {
//            {
//                add(Filters.FilterType.EQUAL);
//                add(Filters.FilterType.NOT_EQUAL);
//                add(Filters.FilterType.GREATER);
//                add(Filters.FilterType.GREATER_EQUAL);
//                add(Filters.FilterType.LESS);
//                add(Filters.FilterType.LESS_EQUAL);
//            }
//        });
//        type_to_operator.put(Attribute.Type.STRING, new ArrayList<Filters.FilterType>() {
//            {
//                add(Filters.FilterType.EQUAL);
//                add(Filters.FilterType.NOT_EQUAL);
//                add(Filters.FilterType.GREATER);
//                add(Filters.FilterType.GREATER_EQUAL);
//                add(Filters.FilterType.LESS);
//                add(Filters.FilterType.LESS_EQUAL);
//            }
//        });
//        type_to_operator.put(Attribute.Type.DATE, new ArrayList<Filters.FilterType>() {
//            {
//                add(Filters.FilterType.EQUAL);
//                add(Filters.FilterType.NOT_EQUAL);
//                add(Filters.FilterType.GREATER);
//                add(Filters.FilterType.GREATER_EQUAL);
//                add(Filters.FilterType.LESS);
//                add(Filters.FilterType.LESS_EQUAL);
//            }
//        });
//    }
//
//    public boolean areCompatible(Attribute.Type type, Filters.FilterType operator) {
//        if (!type_to_operator.containsKey(type))
//            return false;
//        return type_to_operator.get(type).indexOf(operator) != -1;
//    }
}
