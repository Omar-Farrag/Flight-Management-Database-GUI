/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormManipulationStrategies;

import DatabaseManagement.AttributeCollection;
import General.Controller;
import SuperAccess.Form;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ModifyForm implements FormInitializationStrategy {

    private AttributeCollection toPopulateWith;

    public ModifyForm(AttributeCollection toPopulateWith) {
        this.toPopulateWith = toPopulateWith;
    }

    @Override
    public void handleFormInitialization(Form form) {
        form.populateFields(toPopulateWith);
        form.disableUnmodifiableFields();
        form.setLabelType("Modify");
        JButton button = form.getActionBtn();

        ActionListener[] listeners = button.getActionListeners();
        for (ActionListener listener : listeners) {
            button.removeActionListener(listener);
        }

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to modify entry?", "Modification Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION || option == JOptionPane.CLOSED_OPTION) {
                        return;
                    }
                    form.getViewer().applyModification();

                } catch (SQLException ex) {
                    new Controller().displaySQLError(ex);
                }
            }

        });

    }
}
