/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GeneralPurposeSearch;

import DatabaseManagement.Attribute;
import DatabaseManagement.Attribute.Name;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.QueryResult;
import DatabaseManagement.Table;
import FormManipulationStrategies.FilterForm;
import FormManipulationStrategies.InsertForm;
import FormManipulationStrategies.ModifyForm;
import General.Controller;
import SuperAccess.Form;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Dell
 */
public class SearchWindow extends javax.swing.JFrame {

    private AttributeCollection availableAttributes;
    private AttributeCollection worksheetAttributes;
    private ArrayList<WorksheetRow> worksheetRows;
    private Controller controller;
    private final Color violet = new Color(218, 112, 214);

    /**
     * Creates new form SearchWindow
     */
    public SearchWindow() throws SQLException {
        initComponents();
        availableAttributes = new AttributeCollection();
        worksheetAttributes = new AttributeCollection();
        worksheetRows = new ArrayList<>();

        controller = new Controller();
        initListeners();

        refreshAvailableAttributes();
        refreshWorksheetAttributes();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        TopLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        worksheetTable = new javax.swing.JTable();
        TopLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        availableAttributesTable = new javax.swing.JTable();
        TopLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TopLabel1.setBackground(new java.awt.Color(204, 102, 0));
        TopLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TopLabel1.setForeground(new java.awt.Color(255, 255, 255));
        TopLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TopLabel1.setText("Available Attributes");
        TopLabel1.setOpaque(true);

        worksheetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Display", "Visible", "Adjust Filters"
            }
        ));
        worksheetTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(worksheetTable);
        worksheetTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        TopLabel2.setBackground(new java.awt.Color(204, 102, 0));
        TopLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TopLabel2.setForeground(new java.awt.Color(255, 255, 255));
        TopLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TopLabel2.setText("Worksheet");
        TopLabel2.setOpaque(true);

        availableAttributesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Double Click on Attributes to Add them to Worksheet"
            }
        ));
        jScrollPane3.setViewportView(availableAttributesTable);
        availableAttributesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(TopLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TopLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(TopLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(TopLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        TopLabel.setBackground(new java.awt.Color(0, 0, 0));
        TopLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TopLabel.setForeground(new java.awt.Color(255, 255, 255));
        TopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TopLabel.setText("General Purpose Search");
        TopLabel.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(TopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TopLabel;
    private javax.swing.JLabel TopLabel1;
    private javax.swing.JLabel TopLabel2;
    private javax.swing.JTable availableAttributesTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable worksheetTable;
    // End of variables declaration//GEN-END:variables

    private void initListeners() {
        availableAttributesTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON3) {
                    try {
                        int row = availableAttributesTable.getSelectedRow();
                        Attribute attribute = getAvailableAttributesRow(row);

                        worksheetAttributes.add(attribute);

                        refreshAvailableAttributes();
                        refreshWorksheetAttributes();

                    } catch (SQLException ex) {
                        Logger.getLogger(SearchWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        worksheetTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON3) {
                    try {
                        int row = worksheetTable.getSelectedRow();
                        Attribute attribute = getWorksheetRow(row);
                        worksheetAttributes.remove(attribute);
                        refreshAvailableAttributes();
                        refreshWorksheetAttributes();

                    } catch (SQLException ex) {
                        Logger.getLogger(SearchWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void refreshAvailableAttributes() throws SQLException {
        if (worksheetAttributes.isEmpty()) {
            availableAttributes.clear();
            for (Table t : Table.values()) {
                availableAttributes.append(controller.getTableAttributes(t));
            }
            initAvailableAttributesTable();
        } else {
            if (worksheetAttributes.size() == 1) {
                availableAttributes.clear();
                Table t = worksheetAttributes.attributes().iterator().next().getT();
                availableAttributes = controller.getNeighboringAttributes(t);
            }
            initAvailableAttributesTable();
        }
    }

    private void initAvailableAttributesTable() throws SQLException {

        Vector<String> columnNames = new Vector<>();
        columnNames.add("TABLE NAME");
        columnNames.add("ATTRIBUTE NAME");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Attribute attribute : availableAttributes.attributes()) {
            Vector<Object> row = new Vector<Object>();
            row.add(attribute.getT().getTableName().toUpperCase());
            row.add(attribute.getStringName().toUpperCase());
            data.add(row);
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        initTable(availableAttributesTable, model);

    }

    private Attribute getAvailableAttributesRow(int row) {
        Table table = Table.valueOf(availableAttributesTable.getValueAt(row, 0).toString());
        Name name = Name.valueOf(availableAttributesTable.getValueAt(row, 1).toString());

        return new Attribute(name, table);
    }

    private void refreshWorksheetAttributes() throws SQLException {

        worksheetRows.clear();
        for (Attribute attribute : worksheetAttributes.attributes()) {
            worksheetRows.add(new WorksheetRow(attribute));
        }
        initWorksheetTable();
    }

    private void initWorksheetTable() throws SQLException {

        Vector<String> columnNames = new Vector<>();
        columnNames.add("TABLE NAME");
        columnNames.add("ATTRIBUTE NAME");
        columnNames.add("DISPLAY");
        columnNames.add("VISIBILITY");
        columnNames.add("FILTERS");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (WorksheetRow worksheetRow : worksheetRows) {

            Vector<Object> row = new Vector<Object>();
            row.add(worksheetRow.attribute.getT().getTableName().toUpperCase());
            row.add(worksheetRow.attribute.getStringName().toUpperCase());
            row.add(worksheetRow.infoToDisplay);
            row.add(worksheetRow.visibilityCheckbox);
            row.add(worksheetRow.addFiltersBtn);
            data.add(row);

        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        initTable(worksheetTable, model);

        worksheetTable.getColumnModel().getColumn(3).setCellRenderer(new TableComponentRenderer());
        worksheetTable.getColumnModel().getColumn(3).setCellEditor(new CheckboxCellEditor());

        worksheetTable.getColumnModel().getColumn(2).setCellRenderer(new TableComponentRenderer());
        worksheetTable.getColumnModel().getColumn(2).setCellEditor(new ComboBoxCellEditor(new String[]{"Option 1", "Option 2", "Option 3"}));

        worksheetTable.getColumnModel().getColumn(4).setCellRenderer(new TableComponentRenderer());
        worksheetTable.getColumnModel().getColumn(4).setCellEditor(new ButtonCellEditor("Click Me"));

    }

    private Attribute getWorksheetRow(int row) {
        Table t = Table.valueOf(worksheetTable.getValueAt(row, 0).toString());
        Name name = Name.valueOf(worksheetTable.getValueAt(row, 1).toString());
        return new Attribute(name, t);
    }

    private void initTable(JTable table, DefaultTableModel model) {
        table.setModel(model);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setBackground(violet);
        TableRowSorter sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

    }

    class WorksheetRow {

        private Attribute attribute;
        private JComboBox infoToDisplay;
        private JCheckBox visibilityCheckbox;
        private JButton addFiltersBtn;
        private SearchFilters filtersForm;

        private WorksheetRow(Attribute attribute) {
            this.attribute = attribute;
            infoToDisplay = new JComboBox();
            populateInfoToDisplayCMB();

            visibilityCheckbox = new JCheckBox();
            visibilityCheckbox.setSelected(false);

            addFiltersBtn = new JButton();
            addFiltersBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    filtersForm = new SearchFilters(WorksheetRow.this);
                    filtersForm.setVisible(true);
                }
            });
        }

        private void populateInfoToDisplayCMB() {
            infoToDisplay.removeAllItems();
            infoToDisplay.addItem(attribute.getStringName());
            infoToDisplay.addItem("COUNT");

            if (attribute.getType() == Attribute.Type.DATE || attribute.getType() == Attribute.Type.TIMESTAMP) {
                infoToDisplay.addItem("MAX");
                infoToDisplay.addItem("MIN");
            } else if (attribute.getType() == Attribute.Type.NUMBER) {
                infoToDisplay.addItem("MAX");
                infoToDisplay.addItem("MIN");
                infoToDisplay.addItem("SUM");
                infoToDisplay.addItem("AVG");
            }
        }

    }

    public static void main(String[] args) {
        try {
            new SearchWindow().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(SearchWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
