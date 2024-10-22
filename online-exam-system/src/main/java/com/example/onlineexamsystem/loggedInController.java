package com.example.onlineexamsystem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Popup;
import model.question;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

public class loggedInController implements Initializable {
    @FXML
    private Button button_addNext;

    @FXML
    private Button button_logOut;


    @FXML
    private Button button_ok;

    @FXML
    private Button button_submitQuiz;

    @FXML
    private Label lbl_welcome;

    @FXML
    private TextField option1;

    @FXML
    private TextField option2;

    @FXML
    private TextField option3;

    @FXML
    private TextField option4;

    @FXML
    private RadioButton radio_answer1;

    @FXML
    private RadioButton radio_answer2;

    @FXML
    private RadioButton radio_answer3;

    @FXML
    private RadioButton radio_answer4;

    @FXML
    private TextField txt_question;

    @FXML
    private TextField txt_examTitle;

    private ToggleGroup radioGroup;

    private String titleExam = null;

    private HashMap <String, String[]> questions = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButils.changeScene(event, "hello-view.fxml", "Log In", null);
            }
        });

        button_addNext.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                question question = new question();
                String ex = txt_examTitle.getText();
                String qu = txt_question.getText();
                String op1 = option1.getText();
                String op2 = option2.getText();
                String op3 = option3.getText();
                String op4 = option4.getText();
                RadioButton selectedRadio = (RadioButton) radioGroup.getSelectedToggle();


                if (!qu.trim().isEmpty()&&
                        !op1.trim().isEmpty() &&
                        !op2.trim().isEmpty() &&
                        !op3.trim().isEmpty() &&
                        !op4.trim().isEmpty() &&
                        selectedRadio != null &&
                        titleExam != null &&
                        !ex.trim().isEmpty()){
                    String[] data = new String[5];
                    data[0] = option1.getText().trim();
                    data[1] = option2.getText().trim();
                    data[2] = option3.getText().trim();
                    data[3] = option4.getText().trim();
                    data[4] = selectedRadio.getText().trim();
                    questions.put(txt_question.getText().trim(), data);
                    txt_question.clear();
                    option1.clear();
                    option2.clear();
                    option3.clear();
                    option4.clear();

                    Set<String> keyset = questions.keySet();
                    Iterator<String> itr = keyset.iterator();
                    while (itr.hasNext()){
                            String qi = itr.next();
                        String[] values = questions.get(qi);
                        System.out.println(qi);
                        for (String s : values){
                            System.out.println(s);
                        }
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("1. All fields are required. 2. Ensure you click ok to save exam title");
                    alert.show();
                }
            }
        });

        button_ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String title = txt_examTitle.getText();
                if (!title.trim().isEmpty()){
                    titleExam = title;
                    //save to db
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please set an Exam title");
                    alert.show();
                }
            }
        });

        button_submitQuiz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        radioButtonSetup();
    }
    public void setUsername(String username){
        lbl_welcome.setText("Welcome " + username );
    }

    private void radioButtonSetup(){
        radioGroup = new ToggleGroup();
        radio_answer1.setToggleGroup(radioGroup);
        radio_answer2.setToggleGroup(radioGroup);
        radio_answer3.setToggleGroup(radioGroup);
        radio_answer4.setToggleGroup(radioGroup);

    }
}