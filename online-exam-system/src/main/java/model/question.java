package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class question  {
    private quiz quiz;
    int questionId;
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;

    public question(quiz quiz, String question, String option1, String option2, String option3, String option4, String answer) {
        this.quiz = quiz;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public question() {
    }

    public model.quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(model.quiz quiz) {
        this.quiz = quiz;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static void createTable(){
        String query = "SELECT * FROM question";
        String connectionUrl = "jdbc:mysql://localhost:3306/examsystem";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "root", "59959703");
            System.out.println("Connection to question db successful");
            PreparedStatement ps = connection.prepareStatement(query);
            boolean p = ps.execute();
            System.out.println(p  + "question db");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void save(){
        String query = "INSERT INTO question (question, option1,option2, option3, option4, answer) VALUES(?,?,?,?,?,?)";
        String connectionUrl = "jdbc:mysql://localhost:3306/examsystem";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "root", "59959703");
            System.out.println("Connection to question-db successful");
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, this.question);
            ps.setString(2, this.option1);
            ps.setString(3, this.option2);
            ps.setString(4, this.option3);
            ps.setString(5, this.option4);
            ps.setString(6, this.answer);
            ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
