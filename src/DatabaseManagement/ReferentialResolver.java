package DatabaseManagement;

import java.util.*;

public class ReferentialResolver {
    private HashMap<Key, String> column_to_P_Constraint;
    private HashMap<DetailedKey, ArrayList<DetailedKey>> referenced_to_referencers;
    private ArrayList<DetailedKey> primaryKeys;
    private ArrayList<DetailedKey> foreignKeys;
    private ArrayList<DetailedKey> uniqueKeys;

    private static ReferentialResolver instance;

    private ReferentialResolver() {
        column_to_P_Constraint = new HashMap<>();
        referenced_to_referencers = new HashMap<>();
        primaryKeys = new ArrayList<>();
        foreignKeys = new ArrayList<>();
        uniqueKeys = new ArrayList<>();
    }

    public static ReferentialResolver getInstance() {
        return instance == null ? instance = new ReferentialResolver() : instance;
    }

    public void insertPrimary(Table t, Attribute.Name column, String constraintName) {
        primaryKeys.add(new DetailedKey(t, column, constraintName));
        column_to_P_Constraint.put(new Key(t, column), constraintName);
    }

    public void insertForeign(Table t, Attribute.Name column, String constraintName) {
        foreignKeys.add(new DetailedKey(t, column, constraintName));

    }

    public void insertUnique(Table t, Attribute.Name column, String constraintName) {
        uniqueKeys.add(new DetailedKey(t, column, constraintName));
        column_to_P_Constraint.put(new Key(t, column), constraintName);
    }

    public void initResolver() {
        for (DetailedKey key : primaryKeys)
            referenced_to_referencers.put(key, new ArrayList<>());

        for (DetailedKey key : uniqueKeys)
            referenced_to_referencers.put(key, new ArrayList<>());

        for (DetailedKey key : foreignKeys) {
            referenced_to_referencers.get(key).add(key);
        }
    }
//    private String generateConstraintName(String oldConstraintName,){
//
//    }

    private class Key {
        protected Table t;
        protected Attribute.Name column;

        public Key(Table t, Attribute.Name column) {
            this.t = t;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return t == key.t && column == key.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(t, column);
        }
    }

    private class DetailedKey extends Key {
        private String constraintName;

        public DetailedKey(Table table, Attribute.Name column, String constraintName) {
            super(table, column);
            this.constraintName = constraintName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DetailedKey key = (DetailedKey) o;
            return constraintName.equals(key.constraintName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(constraintName);
        }

    }
}
