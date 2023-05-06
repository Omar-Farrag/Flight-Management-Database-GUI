package Queries;

import General.*;
import DatabaseManagement.Attribute;
import DatabaseManagement.Attribute.Name;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.DatabaseManager;
import DatabaseManagement.Exceptions.DBManagementException;
import DatabaseManagement.Filters;
import DatabaseManagement.QueryResult;
import DatabaseManagement.Table;
import FormManipulationStrategies.FilterForm;
import FormManipulationStrategies.InsertForm;
import FormManipulationStrategies.ModifyForm;
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
import java.util.Vector;
import javax.swing.JButton;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TableViewer extends JFrame implements SearchQueryViewer {

    private JTable table;
    private String title;
    private DefaultTableModel model;
    private final Color violet = new Color(218, 112, 214);
    private JPanel panel;
    private JScrollPane scrollPane;
    private final Controller controller = new Controller();
    private FilterFrame form;
    private String query;
    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates a new window displaying the given attribute collection in table
     * format
     *
     * @param title Title of the window
     * @param toShow collection to be retrieved from database and displayed
     * @param form A form containing the frame to be displayed when filtering.
     * Users will interact with that frame to set the filters as they please.
     * Simply create a new JFrame and make it implement the Form interface.
     * Checkout Account for more clarity.
     *
     * @throws SQLException
     */
    public TableViewer(String title, String query, FilterFrame form) throws SQLException, DBManagementException {
        init(title, query, form);
    }

    @Override
    public void applyBrowsingFilters(String filters) throws SQLException {

        ResultSet rs = controller.executeStatement(query + filters);
        refresh(rs);
    }

    private void refresh(ResultSet result) throws SQLException {
        initModel(result);

        initTable();

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        scrollPane.setViewportView(table);
        form.getFrame().dispose();
    }

    private void init(String title, String query, FilterFrame form) throws SQLException {
        this.form = form;
        this.title = title;
        this.query = query;
        form.setViewer(this);

        initModel(controller.executeStatement(query));

        initTable();

        initInnerPanels();

        setTitle(title);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setBackground(Color.white);

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        setVisible(true);
    }

    private void initModel(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        Vector<String> columnNames = new Vector<>();
        for (int i = 0; i < columnCount; i++) {
            columnNames.add(resultSet.getMetaData().getColumnLabel(i + 1));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<Object>();
            for (int i = 0; i < columnCount; i++) {
                row.add(resultSet.getObject(i + 1));
            }
            data.add(row);
        }
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void initTable() {
        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setBackground(violet);
    }

    private void initInnerPanels() {
        panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title);
        label.setFont(new Font("Verdana 24 Bold", Font.BOLD, 24));
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (form != null) {
                    try {
                        Controller controller = new Controller();
                        ResultSet rs = controller.executeStatement(query);
                        refresh(rs);
                    } catch (SQLException ex) {
                        controller.displaySQLError(ex);
                    }

                }
            }
        });
        refreshButton.setBackground(Color.ORANGE);
        refreshButton.setPreferredSize(new Dimension(100, 30));

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                form.initForm();
                form.display(true);
            }
        });
        filterButton.setBackground(Color.ORANGE);
        filterButton.setPreferredSize(new Dimension(100, 30));

        buttonsPanel.add(refreshButton);
        buttonsPanel.add(filterButton);

        panel.add(label, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.white);
        scrollPane.setOpaque(true);

        add(panel, BorderLayout.NORTH);
        add(scrollPane);
    }

}
