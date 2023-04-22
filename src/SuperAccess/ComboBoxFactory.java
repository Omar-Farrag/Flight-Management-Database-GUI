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

    static void populateAirlineCodeCMB(JComboBox toPopulate) {
        Controller controller = new Controller();

        toPopulate.removeAllItems();

        AttributeCollection collection = new AttributeCollection();
        collection.add(new Attribute(Name.CODE, Table.AIRLINE));

        ResultSet result = controller.retrieve(collection).getResult();

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
