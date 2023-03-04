package DatabaseManagement;

public enum Table {
    /*
     * The names of the tables in our tables should appear here.
     * Unfortunately, we cannont create a new schema containing only
     * the tables for this project. Therefore, to distinguish between
     * the tables for our application and all other tables, we can
     * put the names of this application's tables here.
     */
    TABLE1("TABLE1");

    private String tableName;

    private Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
