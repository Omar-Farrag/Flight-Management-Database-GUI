/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General;

import DatabaseManagement.Attribute;
import DatabaseManagement.Attribute.Name;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.DatabaseManager;
import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Filters;
import DatabaseManagement.QueryResult;
import DatabaseManagement.Table;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class Controller {

    private static LoginUser loggedInUser;

    public boolean validLogin(String inputUsername, String inputPassword) throws SQLException, DBManagementException {
        //Passwords in DB are stored in encrypted format
        //We must encrypt it here first to be able to compare it with database passwords
        String encryptedPassword = PasswordManager.encrypt(inputPassword);

        //Retrieve account with the input credentials
        DatabaseManager DB = DatabaseManager.getInstance();

        Filters filters = new Filters();
        filters.addEqual(new Attribute(Name.USERNAME, inputUsername, Table.USERS));
        filters.addEqual(new Attribute(Name.PASSWORD, inputPassword, Table.USERS));

        AttributeCollection toGet = new AttributeCollection();
        toGet.add(new Attribute(Name.USERNAME, Table.USERS));
        toGet.add(new Attribute(Name.NAME, Table.USERS));
        toGet.add(new Attribute(Name.TYPE, Table.USERS));

        QueryResult result = DB.retrieve(toGet, filters);
        if (result.getRowsAffected() > 0) {
            ResultSet rs = result.getResult();
            String username = rs.getString(Name.USERNAME.getName());
            String name = rs.getString(Name.NAME.getName());
            int type = rs.getInt(Name.TYPE.getName());
            loggedInUser = new LoginUser(username, name, type);
            return true;
        }
        return false;
    }

    public LoginUser getLoggedInUser() {
        return loggedInUser;
    }
}
