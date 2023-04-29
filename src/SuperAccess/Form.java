/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SuperAccess;

import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Filters;
import DatabaseManagement.Table;
import FormManipulationStrategies.FormInitializationStrategy;
import General.TableViewer;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public interface Form {

    /**
     *
     * @return The values of all attributes in the form
     */
    public AttributeCollection getAllAttributes();

    /**
     *
     * @return The filters to be used when retrieving tuples from database
     */
    public Filters getBrowsingFilters();

    /**
     *
     * @return Filter for a specific row in the table. Usually used when
     * deleting a specific row
     */
    public Filters getPKFilter();

    /**
     *
     * @return Table that this form is for
     */
    public Table getTable();

    /**
     *
     * @return Frame object that is displaying the form
     */
    public JFrame getFrame();

    /**
     * Enables all fields in the form. Usually used when inserting new tuples in
     * a table to allow the user to enter all values.
     */
    public void enableFields();

    /**
     * Disables certain fields that should not be modified by the user.
     */
    public void disableUnmodifiableFields();

    /**
     * Populates the form fields with the given values
     *
     * @param toPopulateWith AttributeCollection containing all attributes
     * displayed in the form. The values shown on the form will come from the
     * values in the attribute collection
     */
    public void populateFields(AttributeCollection toPopulateWith);

    /**
     * Clears all the fields in the form
     */
    public void clearFields();

    /**
     * Changes the starting word of the top label of the form. Usual values are
     * "FILTER", "INSERT","MODIFY".
     *
     * @param labelType Label to be appended before the table name in the top
     * label
     */
    public void setLabelType(String labelType);

    /**
     *
     * @return Submit button of the form
     */
    public JButton getActionBtn();

    /**
     * Passes to the form a reference of the viewer displaying the form
     *
     * @param viewer
     */
    public void setViewer(TableViewer viewer);

    /**
     *
     * @return Viewer object displaying the form
     */
    public TableViewer getViewer();

    /**
     * Sets the initialization strategy to the given strategy. Strategy is used
     * when displaying the form. For example, if the user selects to insert a
     * new entry, then the form is displayed in insert mode. Similarly, if the
     * user decides to modify a specific row, the form is displayed in
     * modification mode, etc. There are currently three strategies available:
     * InsertForm, ModifyForm, FilterForm
     *
     * @param initStrat Strategy to be applied to this form
     */
    public void setInitStrategy(FormInitializationStrategy initStrat);

    /**
     * Applies the current strategy to the form
     */
    public void applyInitStrategy();

}
