/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import DatabaseManagement.ConstraintsHandling.ConstraintChecker;
import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Table;
import SuperAccess.Account;
import SuperAccess.Airline;
import SuperAccess.Airplane;
import SuperAccess.Airport;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wissam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ConstraintChecker.getInstance();
//        (new LoginForm()).setVisible(true);
            TableViewer viewer4 = new TableViewer("Airport", Table.AIRPORT, new Airport());

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DBManagementException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
