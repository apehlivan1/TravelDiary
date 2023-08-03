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

    @FXML
    private Label greenMessageLabel;

    @FXML
    private Label blackMessageLabel;

    UserManager userManager = new UserManager();

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginClicked(ActionEvent event) throws IOException, AppException {
        if (usernameTextField.getText().isBlank()|| passwordPasswordField.getText().isBlank()) {
            redMessageLabel.setText("Please enter username and password!");
        }
        String labelText = userManager.validateLoginInfo(usernameTextField.getText(), passwordPasswordField.getText());
        if (!labelText.equals("Login successful"))
            redMessageLabel.setText(labelText);
        else {
            //try using stage.setUserData(user);
            User user = userManager.searchByUsername(usernameTextField.getText());
            HomeController homeController = new HomeController(user.getId());

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            fxmlLoader.setController(homeController);
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

            WelcomePageController welcomePageController = new WelcomePageController();
            welcomePageController.close();

            stage.show();
        }
    }

    @FXML
    void passwordOnAction(ActionEvent event) {
        //blackMessageLabel.setText("Password must be 8-15 characters long!");
    }

    @FXML
    void usernameOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
