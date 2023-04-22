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
    ACCOUNT("account", "A"),
    AIRLINE("airline", "B"),
    AIRPLANE("airplane", "C"),
    AIRPORT("airport", "D"),
    BAGGAGE("baggage", "E"),
    BUSINESS_TICKET("business_ticket", "F"),
    CITY("city", "G"),
    CREWMEMBER("crewmember", "H"),
    ECONOMY_TICKET("economy_ticket", "I"),
    FIRST_TICKET("first_ticket", "J"),
    FLIGHT("flight", "K"),
    GATES("gates", "L"),
    PASSENGER("passenger", "M"),
    PERSON("person", "N"),
    PERSON_FLIGHT("person_flight", "O"),
    TICKET("ticket", "P"),
    USER_ACTIVITY("user_activity", "Q"),
    LOCS("LOCS", "Z");

    private final String tableName;
    private final String alias;

    Table(String tableName, String alias) {
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
