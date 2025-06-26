package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private JTable table;
    private Action action;
    private JButton renderButton;
    private JButton editButton;
    private int column;
    private int editingRow;

    public ButtonColumn(JTable table, Action action, int column) {
        this.table = table;
        this.action = action;
        this.column = column;

        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        renderButton.setText((value == null) ? "" : value.toString());
        return renderButton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        editButton.setText((value == null) ? "" : value.toString());
        this.editingRow = row;
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return editButton.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped(); // 편집 종료 후 action 호출
        ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(editingRow));
        action.actionPerformed(event);
    }
}
