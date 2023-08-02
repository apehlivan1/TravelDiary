package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private Label blackMessageLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private Label greenMessageLabel;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Label redMessageLabel;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerClicked(ActionEvent event) throws AppException {
        if (usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank()
                || firstNameField.getText().isBlank() || lastNameField.getText().isBlank()
                || phoneField.getText().isBlank() || emailField.getText().isBlank()) {
            redMessageLabel.setText("All fields are required!");
        }
        //refactor login page -> remove unnecessary onAction
        else {
            User user = new User(0, usernameTextField.getText(), passwordPasswordField.getText(),
                    firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText());
            UserManager userManager = new UserManager();
            userManager.add(user);
        }
    }


}


