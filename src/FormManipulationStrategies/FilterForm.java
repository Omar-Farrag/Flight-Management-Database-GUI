/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormManipulationStrategies;

import General.Controller;
import SuperAccess.Form;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Dell
 */
public class FilterForm implements FormInitializationStrategy {

    @Override
    public void handleFormInitialization(Form form) {
        form.clearFields();
        form.enableFields();
        form.setLabelType("Filter");
        JButton button = form.getActionBtn();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    form.getViewer().applyBrowsingFilters();
                } catch (SQLException ex) {
                    new Controller().displaySQLError(ex);
                }
            }
        });

    }

}
