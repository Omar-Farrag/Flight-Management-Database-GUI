package DatabaseManagement;

import java.util.HashMap;

public class Errors {
    private HashMap<Attribute, String> attribute_to_error;

    private Errors(){
        attribute_to_error = new HashMap<>();
    }

    private void add(Attribute attribute, String errorMessage){
        if(!errorMessage.isEmpty()) attribute_to_error.put(attribute,errorMessage);
    }
    public boolean noErrors(){
        return attribute.to_error.isEmpty();
    }


}
