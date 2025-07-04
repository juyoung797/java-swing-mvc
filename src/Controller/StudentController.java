package Controller;

import Model.Database;
import Model.Student;
import View.Form;
import View.StudentDetails;

import javax.swing.*;
import java.io.File;

public class StudentController {
    // database file
    private String databaseFile = "src\\data\\database.txt";
    private Database database;
    private Form form;
    private StudentDetails studentDetails;

    public StudentController(Form form, StudentDetails studentDetails) {
        this.database = new Database();
        this.form = form;
        this.studentDetails = studentDetails;

        // submit user
        this.form.submitStudent(e -> {
            String studentName = this.form.getStudentName().trim();
            int koreanScore = this.form.getKoreanScore();
            int englishScore = this.form.getEnglishScore();
            int mathScore = this.form.getMathScore();

            // simple validations
            if(studentName.isEmpty()) {
                JOptionPane.showMessageDialog(this.form, "First Name Required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if(koreanScore == 0 || englishScore == 0 || mathScore == 0) {
                JOptionPane.showMessageDialog(this.form, "Score Required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            this.database.addStudent(new Student(studentName, koreanScore, englishScore, mathScore));
            this.database.saveStudent(new File(databaseFile));
            this.form.reset(true);
        });

//        // load users
//        this.form.viewStudents(e -> {
//            this.studentDetails.getStudents(this.database.loadStudents(new File(databaseFile)));
//        });



        this.form.viewStudents(e -> {
            // 데이터 새로 불러오기
            this.database.loadStudents(new File(databaseFile));
            this.studentDetails.getStudents(this.database.loadStudents(new File(databaseFile)));

            // 삭제 리스너 등록
            this.studentDetails.onDeleteStudent(row -> {
                this.database.deleteStudentAt(row); // 메모리에서 삭제
                this.database.saveAll(new File(databaseFile)); // 파일 덮어쓰기
                this.studentDetails.getStudents(this.database.loadStudents(new File(databaseFile)));
            });
        });

    }
}
