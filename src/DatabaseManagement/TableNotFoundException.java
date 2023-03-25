package DatabaseManagement;

public class TableNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Could not find the table in the meta data";
    }

}
