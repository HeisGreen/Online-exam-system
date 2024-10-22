package com.example.onlineexamsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DButils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DButils.class.getResource(fxmlFile));
                root = loader.load();
                loggedInController loggedInController = loader.getController();
                loggedInController.setUsername(username);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                root = FXMLLoader.load(DButils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 550));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String firstname, String lastname, String role) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examsystem", "root", "59959703");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this username");
                alert.show();
            }
            else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password, firstname, lastname, role) VALUES(?, ?, ?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, firstname);
                psInsert.setString(4, lastname);
                psInsert.setString(5, role);
                psInsert.executeUpdate();

                changeScene(event,"logged-in.fxml", "Welcome",username);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examsystem", "root", "59959703");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User does not exist. Please sign up.");
                alert.show();
            }else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)){
                        changeScene(event, "logged-in.fxml", "Welcome !", username);
                    }else {
                        System.out.println("password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
