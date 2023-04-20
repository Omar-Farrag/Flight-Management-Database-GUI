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
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author Dell
 */
public class Controller {

    private static LoginUser loggedInUser;
    private DatabaseManager DB = DatabaseManager.getInstance();

    public boolean validLogin(String inputUsername, String inputPassword) throws SQLException, DBManagementException {
        //Passwords in DB are stored in encrypted format
        //We must encrypt it here first to be able to compare it with database passwords
        String encryptedPassword = PasswordManager.encrypt(inputPassword);

        //Retrieve account with the input credentials
        DatabaseManager DB = DatabaseManager.getInstance();

        Filters filters = new Filters();
        filters.addEqual(new Attribute(Name.USERNAME, inputUsername, Table.ACCOUNT));
        filters.addEqual(new Attribute(Name.PASSWORD, encryptedPassword, Table.ACCOUNT));

        AttributeCollection toGet = new AttributeCollection();
        toGet.add(new Attribute(Name.USERNAME, Table.ACCOUNT));
        toGet.add(new Attribute(Name.ACCOUNT_TYPE, Table.ACCOUNT));

        QueryResult result = DB.retrieve(toGet, filters);
        if (result.getRowsAffected() > 0) {
            ResultSet rs = result.getResult();
            String username = rs.getString(Name.USERNAME.getName());
            String type = rs.getString(Name.ACCOUNT_TYPE.getName());
            loggedInUser = new LoginUser(username, type);
            return true;
        }
        return false;
    }

    public LoginUser getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Displays all errors in a database operation in a separate dialog window
     *
     * @param result The returned QueryResult from the database operation
     */
    public void displayErrors(QueryResult result) {
        ArrayList<String> errors = result.getAllErrors();
        String messageToDisplay = "";
        for (String error : errors) {
            messageToDisplay += "\u2022 " + error + "\n";
        }
        JTextArea text = new JTextArea(messageToDisplay);
//        javax.swing.JLabel label = new javax.swing.JLabel(messageToDisplay);
        text.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        Color bgColor = UIManager.getColor("OptionPane.background");
        text.setBackground(bgColor);
        JOptionPane pane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = pane.createDialog("ERROR");
        dialog.setAlwaysOnTop(true); // make the dialog always on top
        dialog.setVisible(true);
    }

    /**
     * Displays given error message in an error dialog box
     *
     * @param message error message to be displayed;
     */
    public void displayErrors(String message) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = pane.createDialog("ERROR");
        dialog.setAlwaysOnTop(true); // make the dialog always on top
        dialog.setVisible(true);
    }

    /**
     * Displays the SQL error message whenever there is a SQL error
     */
    public void displaySQLError(SQLException e) {
        javax.swing.JLabel label = new javax.swing.JLabel("Something went wrong...Please Try Again Later");
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        JOptionPane pane = new JOptionPane(label, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = pane.createDialog("ERROR");
        dialog.setAlwaysOnTop(true); // make the dialog always on top
        dialog.setVisible(true);

        e.printStackTrace();
    }

    /**
     * Displays a success message in a separate dialog window
     *
     * @param message Message to be displayed in window
     */
    public void displaySuccessMessage(String message) {
        javax.swing.JLabel label = new javax.swing.JLabel(message);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        JOptionPane pane = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog("ERROR");
        dialog.setAlwaysOnTop(true); // make the dialog always on top
        dialog.setVisible(true);
    }

    /**
     * Updates the values of certain rows in the given table
     *
     * @param t Table whose rows are to be modified
     * @param filters Conditions that a row must satisfy to be updated
     * @param toModify Attribute collection containing the attributes to be
     * modified and their new values
     * @param cascade A flag determining whether the changes in the given table
     * must be cascaded to the referencing foreign keys. If they are to be
     * cascaded, then the foreign key attributes referencing them must have the
     * on Update Cascade option in the DBMS. Otherwise, an exception is thrown.
     * @return Result of modification operation
     * @throws SQLException If an error occurs while updating the data in the
     * DB.
     * @throws DBManagementException Print the message to know why the exception
     * was thrown
     */
    public QueryResult modify(Table t, AttributeCollection newValues, Filters filters) {
        try {
            QueryResult result = DB.modify(t, filters, newValues, true);
            if (result.noErrors()) {
                displaySuccessMessage("Entry Modified Successfully!");
            } else {
                displayErrors(result);
            }
            return result;
        } catch (SQLException ex) {
            displaySQLError(ex);
        } catch (DBManagementException ex) {
            displayErrors("Something went wrong while modifying " + t.getTableName().toUpperCase());
        }
        return null;
    }

    /**
     * Inserts the given attribute collection to the given table. The attributes
     * in the collection must match the attributes in the table, but the order
     * is irrelevant. If you want to set one of the attributes in the table to
     * null, then set the value of that attribute in the attribute collection to
     * null, pass an empty string to the attribute's value.
     *
     * @param newValues list of attributes forming the tuple to be inserted
     * @param t Table where the tuple will be inserted
     * @return The result of the insertion operation
     */
    public QueryResult insert(Table t, AttributeCollection newValues) {
        try {
            QueryResult result = DB.insert(t, newValues);
            if (result.noErrors()) {
                displaySuccessMessage("Entry Inserted Successfully!");
            } else {
                displayErrors(result);
            }
            return result;
        } catch (SQLException ex) {
            displaySQLError(ex);
        } catch (DBManagementException ex) {
            displayErrors("Something went wrong while inserting an entry into  " + t.getTableName().toUpperCase());
        }
        return null;
    }

    /**
     * Joins the tables containing the attributes in the given attribute
     * collection and filters then retrieves rows that satisfy certain
     * conditions. Only the attributes in the collection are selected from the
     * rows. Bear in mind that the tables containing the attributes in the
     * attribute collection and filters must be eligible for joining, otherwise
     * an exception is thrown.
     *
     * @param toShow Attributes to be retrieved
     * @param filters Conditions that a row must satisfy to be part of the
     * retrieved set of rows
     * @return QueryResult containing the result set of the retrieval operation
     */
    public QueryResult retrieve(AttributeCollection toShow, Filters filters) {
        try {
            QueryResult result = DB.retrieve(toShow, filters);
            if (!result.noErrors()) {
                displayErrors(result);
            }

            return result;
        } catch (SQLException ex) {
            displaySQLError(ex);
        } catch (DBManagementException ex) {
            displayErrors("Something went wrong while retrieving data from database");
        }
        return null;
    }

    /**
     * Retrieves specific rows from a given table
     *
     * @param t Table containing the rows to be retrieved
     * @param filters Conditions that a row must satisfy to be part of the
     * retrieved set of rows
     * @return QueryResult containing a result set of the retrieved rows
     *
     */
    public QueryResult retrieve(Table t, Filters filters) {
        try {
            QueryResult result = DB.retrieve(t, filters);
            if (!result.noErrors()) {
                displayErrors(result);
            }

            return result;
        } catch (SQLException ex) {
            displaySQLError(ex);
        } catch (DBManagementException ex) {
            displayErrors("Something went wrong while retrieving data from database");
        }
        return null;
    }

    /**
     * Deletes rows in the given table that satisfy all the given filters
     * provided these rows are not referenced by other rows. Passing an empty
     * filters object will delete the whole table.
     *
     * @param t Table whose entries are to be deleted.
     * @param filters Conditions that a row must satisfy to be deleted
     * @return Query result of the delete operation
     */
    public QueryResult delete(Table t, Filters filters) {
        try {
            QueryResult result = DB.delete(t, filters);
            if (result.noErrors()) {
                displaySuccessMessage("Entry Deleted Successfully!");
            } else {
                displayErrors(result);
            }
            return result;
        } catch (SQLException ex) {
            displaySQLError(ex);
        } catch (DBManagementException ex) {
            displayErrors("Something went wrong while deleting an entry from " + t.getTableName().toUpperCase());
        }
        return null;
    }

    /**
     * Retrieves all rows from a specific table
     *
     * @param t Table whose rows are to be retrieved
     * @return QueryResult containing the result set of the retrieved table
     *
     */
    public QueryResult retrieve(Table t) {
        try {
            QueryResult result = DB.retrieve(t);
            if (!result.noErrors()) {
                displayErrors(result);
            }
            return result;
        } catch (SQLException ex) {
            displaySQLError(ex);
        }
        return null;
    }

}
