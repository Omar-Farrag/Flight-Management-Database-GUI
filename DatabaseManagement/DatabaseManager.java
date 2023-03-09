package DatabaseManagement;

public class DatabaseManager implements DatabaseOperations {

    @Override
    public void establishConnection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'establishConnection'");
    }

    @Override
    public void insert() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void modify() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    @Override
    public void retrieve() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    private void extractTables() {
        // Puts tables and their attributes into a metadata json file
        // TODO: Implement ExtractTables
    }

    private void populateConstraints() {
        // Reads the constraints json file and enters the constraints appropriately in
        // metadata.json
        // TODO: Implement populateConstraints

    }

    private void validateConstraints() {
        // tells the constraintChecker class to validate an attibute against given list
        // of constraints
        // TODO: Implement validateConstraints

    }

}
