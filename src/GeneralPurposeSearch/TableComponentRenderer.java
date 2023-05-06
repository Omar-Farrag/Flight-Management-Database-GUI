package GeneralPurposeSearch;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;

public class TableComponentRenderer extends DefaultTableCellRenderer {

    public TableComponentRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Component) {
            return (Component) value;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
