package Model;

public class Student {
    private String name;
    private int koreanScore;
    private int englishScore;
    private int mathScore;
    private double averageScore;
    private int totalScore;

    public Student() {
        // empty constructor
    }

    public Student(String name, int koreanScore, int englishScore, int mathScore) {
        this.name = name;
        this.koreanScore = koreanScore;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
        this.averageScore = (double) Math.round(((koreanScore + englishScore + mathScore) / 3 * 100))/100;
        this.totalScore = koreanScore + englishScore + mathScore;
    }

    // getters
    public String getName() { return name; }
    public int getKoreanScore() { return koreanScore; }
    public int getEnglishScore() { return englishScore; }
    public int getMathScore() { return mathScore; }
    public double getAverageScore() { return averageScore; }
    public int getTotalScore() { return totalScore; }
}
