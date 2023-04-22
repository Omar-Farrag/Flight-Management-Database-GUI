/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SuperAccess;

import DatabaseManagement.Table;
import FormManipulationStrategies.FormInitializationStrategy;
import General.TableViewer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Dell
 */
public abstract class TableForm extends JFrame implements Form {

    protected Table table;
    private JLabel TopLabel;
    private JButton ActionBtn;
    private FormInitializationStrategy initStrategy;
    private TableViewer viewer;

    protected void initBaseComponents(Table table, JLabel TopLabel, JButton ActionBtn) {
        this.table = table;
        this.TopLabel = TopLabel;
        this.ActionBtn = ActionBtn;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public JFrame getFrame() {
        return this;
    }

    @Override
    public void setLabelType(String labelType) {
        TopLabel.setText(labelType.toUpperCase() + " " + table.getTableName().toUpperCase());
    }

    @Override
    public JButton getActionBtn() {
        return ActionBtn;
    }

    @Override
    public TableViewer getViewer() {
        return viewer;
    }

    @Override
    public void setInitStrategy(FormInitializationStrategy initStrat) {
        this.initStrategy = initStrat;
    }

    @Override
    public void applyInitStrategy() {
        initStrategy.handleFormInitialization(this);
    }

    @Override
    public void setViewer(TableViewer viewer) {
        this.viewer = viewer;
    }

}
