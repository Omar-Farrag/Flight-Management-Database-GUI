package DatabaseManagement.Interfaces;

import DatabaseManagement.Exceptions.*;
import DatabaseManagement.QueryResult;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Filters;
import DatabaseManagement.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseOperations {

    Connection getConn();

    DatabaseMetaData getMetaData() throws SQLException;

    QueryResult insert(Table t, AttributeCollection toInsert) throws SQLException, DBManagementException;

    QueryResult delete(Table t, Filters filters) throws SQLException, DBManagementException;

    QueryResult modify(Table t, Filters filters, AttributeCollection toModify) throws SQLException, DBManagementException;

    QueryResult retrieve(Table t) throws SQLException;

    QueryResult retrieve(Table t, Filters filters) throws SQLException, DBManagementException;

    QueryResult retrieve(AttributeCollection toGet) throws SQLException, DBManagementException;

    QueryResult retrieve(AttributeCollection toGet, Filters filters) throws SQLException, DBManagementException;

    ResultSet executeStatement(String sqlStatement) throws SQLException;

    int executePreparedStatement(String sqlPreparedStatement) throws SQLException;

    void printTable(ResultSet employees) throws SQLException;
}
