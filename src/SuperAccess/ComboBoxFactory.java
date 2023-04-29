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

    public static void populateCityCodeCMB(JComboBox toPopulate) {
        Attribute attribute = new Attribute(Name.CODE, Table.CITY);
        fillBoxFromDB(toPopulate, attribute);
    }

    private static void fillBoxFromDB(JComboBox toPopulate, Attribute attribute) {
        AttributeCollection collection = new AttributeCollection();
        collection.add(attribute);

        ResultSet result = controller.retrieve(collection).getResult();

        toPopulate.removeAllItems();
        toPopulate.addItem("");
        try {
            while (result.next()) {
                toPopulate.addItem(result.getString(1));
            }
        } catch (SQLException ex) {
            controller.displaySQLError(ex);
        }
    }

}
