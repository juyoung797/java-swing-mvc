package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Form extends JPanel {

    private JTextField studentNameField;
    private JTextField koreanScoreField;
    private JTextField englishScoreField;
    private JTextField mathScoreField;

    private JButton addButton;
    private JButton viewButton;

    public Form() {

        String[] labels = {"Student Name", "Korean Score", "English Score", "Math Score"};
        JTextField[] fields = new JTextField[labels.length];

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        setLayout(new GridBagLayout());

        // 반복문으로 JLabel과 JTextField 추가
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ": ");
            fields[i] = new JTextField(25);

            // JLabel 추가
            gbc.gridx = 0;
            gbc.gridy = i * 2;
            add(label, gbc);

            // JTextField 추가
            gbc.gridy = i * 2 + 1;
            add(fields[i], gbc);
        }

        // 버튼 영역
        gbc.gridx = 0;
        gbc.gridy = labels.length * 2;
        gbc.insets = new Insets(20, 0, 0, 0);

        addButton = new JButton("Add Student Score");
        addButton.setPreferredSize(new Dimension(278, 40));
        add(addButton, gbc);

        gbc.gridy++;
        viewButton = new JButton("View All Students");
        viewButton.setPreferredSize(new Dimension(278, 40));
        add(viewButton, gbc);

        // 필드 참조 연결
        studentNameField = fields[0];
        koreanScoreField = fields[1];
        englishScoreField = fields[2];
        mathScoreField = fields[3];


    }

    // getters
    public String getStudentName() {
        return studentNameField.getText();
    }

    public int getKoreanScore() {
        return Integer.parseInt(koreanScoreField.getText());
    }

    public int getEnglishScore() {
        return Integer.parseInt(englishScoreField.getText());
    }

    public int getMathScore() {
        return Integer.parseInt(mathScoreField.getText());
    }

    public void submitStudent(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void viewStudents(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }

    // reset fields
    public void reset(boolean bln) {
        if(bln) {
            studentNameField.setText("");
            koreanScoreField.setText("");
            englishScoreField.setText("");
            mathScoreField.setText("");
        }
    }
}
