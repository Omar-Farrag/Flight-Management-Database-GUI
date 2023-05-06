/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SuperAccess;

import DatabaseManagement.Attribute;
import DatabaseManagement.Attribute.Name;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.QueryResult;
import DatabaseManagement.Table;
import General.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Dell
 */
public class ComboBoxFactory {

    private static Controller controller = new Controller();

    /**
     *
     * @return JComboBox initialized with a list of all account types as
     * described in the database
     */
    public static void populateAccountTypeCMB(JComboBox toPopulate) {

        toPopulate.removeAllItems();
        toPopulate.addItem("");
        toPopulate.addItem("0");
        toPopulate.addItem("1");

    }

    public static void populateAirlineCodeCMB(JComboBox toPopulate) {

        Attribute attribute = new Attribute(Name.CODE, Table.AIRLINE);
        fillBoxFromDB(toPopulate, attribute);

    }

    public static void populateAirportNameCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.NAME, Table.AIRPORT);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populateCityCodeCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.CODE, Table.CITY);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populateFlightNumberCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.FNUMBER, Table.FLIGHT);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populatePersonCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.SSN, Table.PERSON);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populatePassengerCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.SSN, Table.PASSENGER);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populateCrewMemberCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.SSN, Table.CREWMEMBER);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populateCrewRoleCMB(JComboBox toPopulate) {
        toPopulate.removeAllItems();
        toPopulate.addItem("");
        toPopulate.addItem("Pilot");
        toPopulate.addItem("Co-Pilot");
        toPopulate.addItem("Flight Attendant");
        toPopulate.addItem("Air Marshall");
    }

    public static void populateLoungeCMB(JComboBox toPopulate) {
        toPopulate.removeAllItems();
        toPopulate.addItem("");
        toPopulate.addItem("A");
        toPopulate.addItem("B");
        toPopulate.addItem("C");
        toPopulate.addItem("D");
        toPopulate.addItem("E");
        toPopulate.addItem("F");
        toPopulate.addItem("G");
    }

    public static void populateTicketNumCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.NUM, Table.TICKET);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populateAirportFloorCMB(JComboBox toPopulate) {
        toPopulate.removeAllItems();
        toPopulate.addItem("");
        toPopulate.addItem("1");
        toPopulate.addItem("2");
        toPopulate.addItem("3");
        toPopulate.addItem("4");
    }

    public static void populateAirportCodeCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.CODE, Table.AIRPORT);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populateTablesCMB(JComboBox toPopulate) {
        toPopulate.removeAllItems();
        ArrayList<String> tableNames = Table.getApplicationTables();
        for (String tableName : tableNames) {
            toPopulate.addItem(tableName);
        }
        if (!Controller.userIsAdmin()) {
            toPopulate.removeItem(Table.USER_ACTIVITY.getTableName().toUpperCase());
            toPopulate.removeItem(Table.ACCOUNT.getTableName().toUpperCase());
        }
    }

    public static void populateStatusCMB(JComboBox toPopulate) {
        toPopulate.removeAllItems();
        toPopulate.addItem("");
        toPopulate.addItem("D");
        toPopulate.addItem("A");
        toPopulate.addItem("N");

        toPopulate.setSelectedItem("N");
    }

    public static void populateGateNumberCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.GNUMBER, Table.GATES);
        fillBoxFromDB(toPopulate, attribute);
    }

    public static void populateAirplaneNumberCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.NUM, Table.AIRPLANE);
        fillBoxFromDB(toPopulate, attribute);
    }

    private static void fillBoxFromDB(JComboBox toPopulate, Attribute attribute) {
        AttributeCollection collection = new AttributeCollection();
        collection.add(attribute);

        ResultSet result = controller.retrieve(collection).getResult();

        toPopulate.removeAllItems();
        HashSet<String> itemsAdded = new HashSet<>();

        try {
            String toAdd = "";
            toPopulate.addItem(toAdd);
            itemsAdded.add(toAdd);

            while (result.next()) {
                toAdd = result.getString(1);

                if (!itemsAdded.contains(toAdd)) {
                    itemsAdded.add(toAdd);
                    toPopulate.addItem(toAdd);
                }

            }
        } catch (SQLException ex) {
            controller.displaySQLError(ex);
        }
    }

}
