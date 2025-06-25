package View;

import Controller.StudentController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // Card layout for switching view
    private CardLayout cardLayout;
    public static final String FORM_VIEW = "form";
    public static final String DETAILS_VIEW = "student details";


    public MainFrame() {
        super("Java Swing MVC");
        cardLayout = new CardLayout();
        Form form = new Form();
        StudentDetails studentDetails = new StudentDetails();
        // sets our layout as a card layout
        setLayout(cardLayout);

        // initialize user controller
        new StudentController(form, studentDetails);

        // adds view to card layout with unique constraints
        add(form, FORM_VIEW);
        add(studentDetails, DETAILS_VIEW);

        // switch view according to its constraints on click
        form.viewStudents(e -> cardLayout.show(MainFrame.this.getContentPane(), DETAILS_VIEW));
        studentDetails.backButton(e -> cardLayout.show(MainFrame.this.getContentPane(), FORM_VIEW));

        // icon for our application
        ImageIcon imageIcon = new ImageIcon("src/assets/default.png");
        setIconImage(imageIcon.getImage());
        // frame width & height
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        // size of our application frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
