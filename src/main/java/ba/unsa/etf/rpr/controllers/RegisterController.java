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
    void cancelClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        newStage("/fxml/welcome page.fxml", null);
    }

    @FXML
    void registerClicked(ActionEvent event) throws AppException, IOException {
        if (usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank()
                || firstNameField.getText().isBlank() || lastNameField.getText().isBlank()
                || phoneField.getText().isBlank() || emailField.getText().isBlank()) {
            redMessageLabel.setText("All fields are required!");
        }
        else {
            User user = new User(0, usernameTextField.getText(), passwordPasswordField.getText(),
                    firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText());
            UserManager userManager = new UserManager();
            user = userManager.add(user);
            HomeController homeController = new HomeController(user.getId());
            newStage("/fxml/home.fxml", homeController);

            ((Stage) registerButton.getScene().getWindow()).close();
        }
    }

    private void newStage(String resource, Object controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
        if (controller != null)
            fxmlLoader.setController(controller);
        else
            stage.initStyle(StageStyle.UNDECORATED);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }


}


