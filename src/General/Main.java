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
            TableViewer viewer1 = new TableViewer("Account", Table.ACCOUNT, new Account());
            TableViewer viewer2 = new TableViewer("Airline", Table.AIRLINE, new Airline());
            TableViewer viewer3 = new TableViewer("Airplane", Table.AIRPLANE, new Airplane());

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DBManagementException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
