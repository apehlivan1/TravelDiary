package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegisterController {

    private User user;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

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
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()).close();
        newStage("/fxml/welcome page.fxml", false);
    }

    @FXML
    void registerClicked(ActionEvent event) throws AppException, IOException {
        if (usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank()
                || firstNameField.getText().isBlank() || lastNameField.getText().isBlank()
                || phoneField.getText().isBlank() || emailField.getText().isBlank()) {
            redMessageLabel.setText("All fields are required!");
        }
        else {
             user = new User(0, usernameTextField.getText(), passwordPasswordField.getText(),
                    firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText());
             UserManager userManager = new UserManager();
             user = userManager.add(user);
             newStage("/fxml/home.fxml", true);

             ((Stage) registerButton.getScene().getWindow()).close();
        }
    }

    private void newStage(String resource, Boolean passInfo) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Parent root = loader.load();
        if (passInfo) {
            HomeController controller = loader.getController();
            controller.setUserId(user.getId());
        }
        else
            stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }
}


