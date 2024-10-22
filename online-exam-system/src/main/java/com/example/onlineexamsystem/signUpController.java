package com.example.onlineexamsystem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class signUpController implements Initializable{

    @FXML
    private ComboBox<String> comboRole;

    @FXML
    private Button button_logIn;

    @FXML
    private Button button_signUp;

    @FXML
    private Label lbl_firstName;

    @FXML
    private Label lbl_lastName;

    @FXML
    private Label lbl_password;

    @FXML
    private Label lbl_question;

    @FXML
    private Label lbl_role;

    @FXML
    private Label lbl_userName;

    @FXML
    private TextField txt_firstName;

    @FXML
    private TextField txt_lastName;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_userName;




    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboRole.getItems().addAll("Teacher", "Student");

        button_signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!txt_userName.getText().trim().isEmpty() && !txt_password.getText().trim().isEmpty() && !txt_firstName.getText().trim().isEmpty() && !txt_lastName.getText().trim().isEmpty() && comboRole.getValue() != null && !comboRole.getValue().isEmpty()){
                    DButils.signUpUser(event, txt_userName.getText(), txt_password.getText(), txt_firstName.getText(),txt_lastName.getText(), comboRole.getValue());
                }else {
                    System.out.println("Please fill in all credentials");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill in all fields");
                    alert.show();
                }
            }
        });
        button_logIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButils.changeScene(event, "hello-view.fxml", "Log in", null);
            }
        });
    }

}
