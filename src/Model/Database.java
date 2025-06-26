package Model;

import java.io.*;
import java.util.ArrayList;

public class Database {

    private ArrayList<Student> userArrayList;

    public Database() {
        userArrayList = new ArrayList<>();
    }

    // adds student to our collection
    public void addStudent(Student student) {
        userArrayList.add(student);
    }

    // saves student to database file
    public void saveStudent(File file) {
        try {
            // student model
            Student student;
            String save_data = "";

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            int i = 0;
            while( i < userArrayList.size()) {
                student = userArrayList.get(i);
                save_data = student.getName() + ", "
                        + student.getKoreanScore() + ", "
                        + student.getEnglishScore() + ", "
                        + student.getMathScore() + ", "
                        + student.getAverageScore() + ", "
                        + student.getTotalScore();
                i++;
            }
            bufferedWriter.write(save_data);
            bufferedWriter.newLine();
            // prevents memory leak
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads student from database file
    public Object[] loadStudents(File file) {
        Object[] objects;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            // each lines to array
            objects = bufferedReader.lines().toArray();
            bufferedReader.close();
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteStudentAt(int index) {
        if (index >= 0 && index < userArrayList.size()) {
            userArrayList.remove(index);
        }
    }

    // 전체 데이터 저장 (덮어쓰기)
    public void saveAll(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student student : userArrayList) {
                String line = student.getName() + ", " + student.getKoreanScore()
                        + ", " + student.getEnglishScore() + ", " + student.getMathScore();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
