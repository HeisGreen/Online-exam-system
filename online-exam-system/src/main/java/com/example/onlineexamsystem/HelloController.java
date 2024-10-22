package com.example.onlineexamsystem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button button_logIn;

    @FXML
    private Button button_signUp;

    @FXML
    private Label lbl_password;

    @FXML
    private Label lbl_question;

    @FXML
    private Label lbl_username;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButils.logInUser(event, txt_username.getText(), txt_password.getText());
            }
        });

        button_signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButils.changeScene(event, "sign-up.fxml" , "Sign Up", null);
            }
        });
    }
}