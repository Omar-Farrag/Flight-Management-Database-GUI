package DatabaseManagement;

public enum Table {
    /*
     * The names of the tables in our tables should appear here.
     * Unfortunately, we cannont create a new schema containing only
     * the tables for this project. Therefore, to distinguish between
     * the tables for our application and all other tables, we can
     * put the names of this application's tables here.
     */
    // DEPT("DEPT"),BONUS("BONUS"),EMP("EMP"),SALGRADE("SALGRADE");
    UTILITY_CONSUMPTION("UTILITY_CONSUMPTION"),
    MAINTENANCE_REQUESTS("MAINTENANCE_REQUESTS"),
    BILLS("BILLS"),
    RECEIPTS("RECEIPTS"),
    DISCOUNTS("DISCOUNTS"),
    LEASES("LEASES"),
    LEASE_REQUESTS("LEASE_REQUESTS"),
    APPOINTMENT_SLOTS("APPOINTMENT_SLOTS"),
    APPOINTMENTS("APPOINTMENTS"),
    PROPERTIES("PROPERTIES"),
    LOCS("LOCS"),
    MALLS("MALLS"),
    USERS("USERS"),
    ROLES("ROLES"),
    CREDENTIALS("CREDENTIALS"),
    NOTIFICATIONS("NOTIFICATIONS");

    private String tableName;

    private Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
