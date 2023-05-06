/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneralPurposeSearch;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Dell
 */
class CheckboxCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JCheckBox checkBox;

    public CheckboxCellEditor() {
        checkBox = new JCheckBox();
        checkBox.setOpaque(true);
    }

    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        checkBox.setSelected((Boolean) value);
        return checkBox;
    }
}
