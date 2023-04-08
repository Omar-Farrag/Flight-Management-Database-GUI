package DatabaseManagement;

import java.util.ArrayList;

public enum Table {
    /*
     * The names of the tables in our tables should appear here.
     * Unfortunately, we cannont create a new schema containing only
     * the tables for this project. Therefore, to distinguish between
     * the tables for our application and all other tables, we can
     * put the names of this application's tables here.
     */

    private final String tableName;
    private final String alias;

    Table(String
    tableName, String
    alias


        ) {
        this.tableName = tableName;
        this.alias = alias;
    }

    public static ArrayList<String> getApplicationTables() {
        ArrayList<String> currentApplicationTables = new ArrayList<>();
        for (Table t : Table.values()) {
            currentApplicationTables.add(t.getTableName().toUpperCase());
        }
        return currentApplicationTables;
    }

    public String getTableName() {
        return tableName;
    }

    public String getAlias() {
        return alias;
    }

    public String getAliasedName() {
        return getTableName() + " " + alias;
    }
}
