package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label redMessageLabel;

    @FXML
    private Label greenMessageLabel;

    @FXML
    private Label blackMessageLabel;

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginClicked(ActionEvent event) {
        if (usernameTextField.getText().isBlank() == true || passwordPasswordField.getText().isBlank() == true) {
            redMessageLabel.setText("Please enter username and password!");
        }
    }

    @FXML
    void passwordOnAction(ActionEvent event) {
        //blackMessageLabel.setText("Password must be 8-15 characters long!");
    }

    @FXML
    void usernameOnAction(ActionEvent event) {

    }

}
