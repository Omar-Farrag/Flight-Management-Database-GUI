package DatabaseManagement.Interfaces;

import DatabaseManagement.Constraints.ReferentialResolver;
import DatabaseManagement.Tables.Attribute;
import DatabaseManagement.Tables.AttributeCollection;
import DatabaseManagement.Tables.Filters;
import DatabaseManagement.Tables.Table;

import java.util.HashMap;

public interface ForeignIntegrityInterface {

    ReferentialResolver.DetailedKey getReferencedTable(String constraintName);

    AttributeCollection getReferencedAttributes(Table t);

    HashMap<Table, Filters> getReferencingAttributes(Table t, Attribute attribute);

    void insertPrimary(Table t, Attribute.Name column, String constraintName);

    void insertForeign(Table t, Attribute.Name column, String constraintName);

    void insertUnique(Table t, Attribute.Name column, String constraintName);

    void initResolver();
}
