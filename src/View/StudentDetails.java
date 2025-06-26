package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.function.Consumer;

public class StudentDetails extends JPanel {

    // Table for user data
    private JTable userTable;
    // table column
    private String[] userTableColumn = {"NAME", "Korean Score", "English Score", "Math Score", "Average Score", "Total Score", "edit", "delete"};

    // back button
    private JButton addButton;

    public StudentDetails() {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        userTable = new JTable();
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        addButton = new JButton("추가");
        add(toolBar);
        toolBar.add(addButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);
    }

    // gets students from database and loads to table
    public void getStudents(Object[] objects) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) userTable.getModel();
        defaultTableModel.setColumnIdentifiers(userTableColumn);
        int i = 0;
        while(i < objects.length) {
            String row = objects[i].toString().trim();
            String[] rows = row.split(",");
            // 버튼용 텍스트 추가
            String[] fullRow = Arrays.copyOf(rows, rows.length+2);
            fullRow[rows.length] = "Edit";
            fullRow[rows.length+1] = "Delete";
            defaultTableModel.addRow(fullRow);
            i++;

        }
        // add after the table is populated
        // edit 버튼 기능 추가
        Action editAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                // 예시: 편집할 학생 이름만 출력
                String name = (String) userTable.getValueAt(row, 0); // 첫 번째 컬럼: 이름
                JOptionPane.showMessageDialog(null, "Edit clicked for: " + name);
            }
        };
        new ButtonColumn(userTable, editAction, 6); // 6번 열이 "Edit"

        // Delete 버튼 기능 추가
        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());

                int confirm = JOptionPane.showConfirmDialog(null,
                        "정말 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) userTable.getModel();
                    model.removeRow(row); // 테이블에서만 삭제됨

                    System.out.println("Deleted row: " + row);
                }
            }
        };
        new ButtonColumn(userTable, deleteAction, 7); // 7번 열이 Delete

    }

    // event listener for back button
    public void addButton(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }


    // StudentDetails.java에 메서드 추가

    public void onDeleteStudent(Consumer<Integer> consumer) {
        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                int confirm = JOptionPane.showConfirmDialog(null,
                        "정말 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    consumer.accept(row); // Controller에게 row 전달
                }
            }
        };
        new ButtonColumn(userTable, deleteAction, 7); // 7번째 열: Delete
    }



}
