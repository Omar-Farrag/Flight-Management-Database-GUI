public interface DatabaseOperations {
    // Just a simple iterface for our database manager class

    public void establishConnection();

    // Inserts an entry into the database
    public void insert();

    // Deletes a set of entries from the database
    public void delete();

    // Modifies a set of entries in the database
    public void modify();

    // retrieves data from the database
    public void retrieve();
}
