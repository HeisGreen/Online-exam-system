package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class quiz {
    private int quizId;

    private String title;

    public quiz(String title) {
        this.title = title;
    }

    public int getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "quiz{" +
                "quizId=" + quizId +
                ", title='" + title + '\'' +
                '}';
    }

    public static void createTable(){
        String query = "SELECT * FROM quiz";
        String connectionUrl = "jdbc:mysql://localhost:3306/examsystem";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "root", "59959703");
            System.out.println("Connection to db successful");
            PreparedStatement ps = connection.prepareStatement(query);
            boolean p = ps.execute();
            System.out.println(p);
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        }

        public int save(){
            String query = "INSERT INTO quiz (title) VALUES(?)";
            String connectionUrl = "jdbc:mysql://localhost:3306/examsystem";
            try {
                Connection connection = DriverManager.getConnection(connectionUrl, "root", "59959703");
                System.out.println("Connection to quiz-db successful");
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, this.title);
                ps.executeUpdate();

            }catch (Exception e){
                e.printStackTrace();
            }
            return -1;
        }
        public boolean save(ArrayList questions){
        boolean flag = false;
        return flag;
        }
}