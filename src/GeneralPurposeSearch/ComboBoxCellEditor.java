/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneralPurposeSearch;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Dell
 */
class ComboBoxCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JComboBox<String> comboBox;

    public ComboBoxCellEditor(String[] items) {
        comboBox = new JComboBox<>(items);
        comboBox.setOpaque(true);
    }

    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        comboBox.setSelectedItem(value);
        return comboBox;
    }
}
