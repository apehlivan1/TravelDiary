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

public class LoginController {

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

    private UserManager userManager = new UserManager();

    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()).close();
        newStage("/fxml/welcome page.fxml", null);
    }

    @FXML
    void loginClicked(ActionEvent event) throws IOException, AppException {
        if (usernameTextField.getText().isBlank()|| passwordPasswordField.getText().isBlank()) {
            redMessageLabel.setText("Please enter username and password!");
        }
        else {
            String labelText = userManager.validateLoginInfo(usernameTextField.getText(), passwordPasswordField.getText());
            if (!labelText.equals("Login successful"))
                redMessageLabel.setText(labelText);
            else {
                User user = userManager.searchByUsername(usernameTextField.getText());
                HomeController homeController = new HomeController(user.getId());
                newStage("/fxml/home.fxml", homeController);
                ((Stage) loginButton.getScene().getWindow()).close();
            }
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
