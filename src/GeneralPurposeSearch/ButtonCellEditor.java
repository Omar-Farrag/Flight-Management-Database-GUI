/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneralPurposeSearch;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Dell
 */
class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;

    public ButtonCellEditor(String text) {
        button = new JButton(text);
        button.setOpaque(true);
    }

    public Object getCellEditorValue() {
        return button.getText();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        button.setText((String) value);
        return button;
    }
}
