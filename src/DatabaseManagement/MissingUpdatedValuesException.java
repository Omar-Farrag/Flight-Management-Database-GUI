package DatabaseManagement;

public class MissingUpdatedValuesException extends Exception {

    private Table t;

    public MissingUpdatedValuesException(Table t) {
        this.t = t;
    }

    @Override
    public String getMessage() {
        return "You did not provide new values when updating table " + t.getTableName() + ". Please" +
                " ensure you did not provide an empty list";
    }
}
