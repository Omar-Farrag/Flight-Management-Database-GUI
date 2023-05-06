/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Queries;

import General.Controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public abstract class FilterFrame extends JFrame {

    private SearchQueryViewer viewer;
    protected final Controller controller = new Controller();

    public void display(boolean visibility) {
        this.setVisible(visibility);
    }

    public void setViewer(SearchQueryViewer viewer) {
        this.viewer = viewer;
    }

    public JFrame getFrame() {
        return this;
    }

    protected abstract void initForm();

    protected abstract HashSet<String> validateInput();

    protected abstract String prepareFilterClause();

    protected void handleFormSubmission() {
        HashSet<String> errors = validateInput();
        if (errors.isEmpty()) {
            applyFilters(prepareFilterClause());
            dispose();
        } else {
            controller.displayErrors(errors);
        }
    }

    protected void applyFilters(String filters) {
        try {
            viewer.applyBrowsingFilters(filters);
        } catch (SQLException ex) {
            controller.displaySQLError(ex);
        }
    }
}
