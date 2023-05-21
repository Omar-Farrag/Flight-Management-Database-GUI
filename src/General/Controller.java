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
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
            rs.next();
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

    public static boolean userIsAdmin() {
        return loggedInUser.isAdmin();
    }

    /**
     * Displays all errors in a database operation in a separate dialog window
     *
     * @param result The returned QueryResult from the database operation
     */
    public void displayErrors(QueryResult result) {
        displayErrors(result.getAllErrors());
    }

    public void displayErrors(HashSet<String> errors) {
        String messageToDisplay = "";
        for (String error : errors) {
            messageToDisplay += "\u2022 " + error + "\n";
        }
        JTextArea text = new JTextArea(messageToDisplay);
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
        JDialog dialog = pane.createDialog("SUCCESS");
        dialog.setAlwaysOnTop(true); // make the dialog always on top
        dialog.setVisible(true);
    }

    /**
     * Updates the values of certain rows in the given table. Displays a
     * success/error message upon completion
     *
     * @param t Table whose rows are to be modified
     * @param filters Conditions that a row must satisfy to be updated
     * @param newValues Attribute collection containing the attributes to be
     * modified and their new values
     * @return result of the modification operation
     *
     */
    public QueryResult modify(Table t, AttributeCollection newValues, Filters filters) {
        return modifyHelper(t, newValues, filters, false);
    }

    /**
     * Updates the values of certain rows in the given table. Does not display a
     * success/error message upon completion
     *
     * @param t Table whose rows are to be modified
     * @param filters Conditions that a row must satisfy to be updated
     * @param newValues Attribute collection containing the attributes to be
     * modified and their new values
     * @return result of the modification operation
     *
     */
    public QueryResult quietModify(Table t, AttributeCollection newValues, Filters filters) {
        return modifyHelper(t, newValues, filters, true);
    }

    private QueryResult modifyHelper(Table t, AttributeCollection newValues, Filters filters, boolean quiet) {
        try {
            QueryResult result = DB.modify(t, filters, newValues, true);
            if (quiet) {
                return result;
            }
            if (result.noErrors()) {
                displaySuccessMessage("Entry Modified Successfully!");
            } else {
                displayErrors(result);
            }
            return result;
        } catch (SQLException ex) {
            displaySQLError(ex);
        } catch (DBManagementException ex) {
            ex.printStackTrace();
            displayErrors("Something went wrong while modifying " + t.getTableName().toUpperCase());
        }
        return null;
    }

    /**
     * Inserts the given attribute collection to the given table. The attributes
     * in the collection must match the attributes in the table, but the order
     * is irrelevant. If you want to set one of the attributes in the table to
     * null, then set the value of that attribute in the attribute collection to
     * null, pass an empty string to the attribute's value. Displays a
     * success/error message upon completion
     *
     * @param newValues list of attributes forming the tuple to be inserted
     * @param t Table where the tuple will be inserted
     * @return The result of the insertion operation
     */
    public QueryResult insert(Table t, AttributeCollection newValues) {
        return insertHelper(t, newValues, false);
    }

    /**
     * Inserts the given attribute collection to the given table. The attributes
     * in the collection must match the attributes in the table, but the order
     * is irrelevant. If you want to set one of the attributes in the table to
     * null, then set the value of that attribute in the attribute collection to
     * null, pass an empty string to the attribute's value. Does not display a
     * success/error message upon completion
     *
     * @param newValues list of attributes forming the tuple to be inserted
     * @param t Table where the tuple will be inserted
     * @return The result of the insertion operation
     */
    public QueryResult quietInsert(Table t, AttributeCollection newValues) {
        return insertHelper(t, newValues, true);
    }

    public QueryResult insertHelper(Table t, AttributeCollection newValues, boolean quiet) {
        try {
            QueryResult result = DB.insert(t, newValues);
            if (quiet) {
                return result;
            }
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
     * Deletes rows in the given table that satisfy all the given filters
     * provided these rows are not referenced by other rows. Passing an empty
     * filters object will delete the whole table. Displays a success/error
     * message after completion
     *
     * @param t Table whose entries are to be deleted.
     * @param filters Conditions that a row must satisfy to be deleted
     * @return Query result of the delete operation
     */
    public QueryResult delete(Table t, Filters filters) {
        return deleteHelper(t, filters, false);
    }

    /**
     * Deletes rows in the given table that satisfy all the given filters
     * provided these rows are not referenced by other rows. Passing an empty
     * filters object will delete the whole table. Does not display a
     * success/error message after completion
     *
     * @param t Table whose entries are to be deleted.
     * @param filters Conditions that a row must satisfy to be deleted
     * @return Query result of the delete operation
     */
    public QueryResult quiteDelete(Table t, Filters filters) {
        return deleteHelper(t, filters, true);
    }

    private QueryResult deleteHelper(Table t, Filters filters, boolean quiet) {
        try {
            QueryResult result = DB.delete(t, filters);

            if (quiet) {
                return result;
            }

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

    /**
     * Retrieves all rows from the tables containing the attributes in the given
     * collection. Only the attribute values are retrieved
     *
     * @param toGet Table whose rows are to be retrieved
     * @return QueryResult containing the result set of the retrievedd e
     */
    public QueryResult retrieve(AttributeCollection toGet) {
        try {
            QueryResult result = DB.retrieve(toGet);
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
     * Executes the given SQL statement. Only Select SQL statements allowed
     *
     * @param sqlStatement Statement to be executed
     * @return Result set of the executed statement
     * @throws SQLException If an error occurs while executing the SQL statement
     * in the DBMS
     */
    public ResultSet executeStatement(String sqlStatement) throws SQLException {

        try {
            return DB.executeStatement(sqlStatement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(sqlStatement);
            throw new SQLException();
        }

    }

    /**
     * Formats a given timestamp to the following string format: dd-MMM-yyyy
     * hh:mm:ss a. Particularly useful when reading timestamps from result sets.
     * By default, reading a time stamp from a result set as a string changes
     * the timestamp format from dd-MMM-yy hh:mm:ss.SSSSSSSSS a to yyyy-mm-dd
     * HH:mm:ss. Use this function to format the string to the default format.
     *
     * @param stamp timestamp to be formatted
     * @return given timestamp as a string in the format 'd-MMM-yyyy hh:mm:ss a'
     */
    public String formatTimeStamp(Timestamp stamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        return formatter.format(stamp);
    }

    /**
     * Retrieves the attributes of the given table
     *
     * @param t Table whose attributes are to be retrieved
     * @return An AttributeCollection consisting of all the attributes in table
     * t
     */
    public AttributeCollection getTableAttributes(Table t) {
        return DB.getAttributes(t);
    }

    /**
     * Returns all attributes in tables that can be joined with the given table
     *
     * @param t
     * @return
     */
    public AttributeCollection getNeighboringAttributes(Table t) {

        return addNeighbors(t, new HashSet<>(), new AttributeCollection());
    }

    private AttributeCollection addNeighbors(Table table, Set<Table> visited, AttributeCollection accumulator) {

        accumulator.append(getTableAttributes(table));
        visited.add(table);
        Set<Table> neighbors = getNeighbors(table);
        if (neighbors.isEmpty()) {
            return accumulator;
        }
        if (accumulator.attributes().containsAll(neighbors)) {
            return accumulator;
        }

        for (Table t : neighbors) {
            if (!visited.contains(t)) {
                addNeighbors(t, visited, accumulator);
            }
        }
        return accumulator;
    }

    private Set<Table> getNeighbors(Table t) {
        return DB.getNeighbors(t);
    }

    public String validateType(Attribute attribute) {
        return DB.validateType(attribute);
    }

    /**
     * Logs an interaction between the logged in user and the database
     *
     * @param result QueryResult of the database operation
     * @param tables Array of tables that was affected;
     */
    public void logActivity(QueryResult result, Table[] tables) {
        String numRows = String.valueOf(result.getRowsAffected());
        String activityDate = formatTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
        String user = loggedInUser.getUsername();
        String activity = result.getQuery().toUpperCase();

        for (Table table : tables) {
            if (table == Table.USER_ACTIVITY) {
                continue;
            }
            AttributeCollection collection = new AttributeCollection();
            collection.add(new Attribute(Name.ROWS_AFFECTED, numRows, Table.USER_ACTIVITY));
            collection.add(new Attribute(Name.ACTIVITY_DATE, activityDate, Table.USER_ACTIVITY));
            collection.add(new Attribute(Name.ACCOUNT_USERNAME, user, Table.USER_ACTIVITY));
            collection.add(new Attribute(Name.ACTIVITY, activity, Table.USER_ACTIVITY));
            collection.add(new Attribute(Name.TABLE_NAME, table.getTableName(), Table.USER_ACTIVITY));
            quietInsert(Table.USER_ACTIVITY, collection);
        }
    }
}
