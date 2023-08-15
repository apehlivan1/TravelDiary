package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
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

/**
 * Controller class for logging in.
 *
 * @author Almedina Pehlivan
 */
public class LoginController {

    private User user;

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

    /**
     * Handles the action event generated by the cancel button.
     *
     * @throws IOException check newStage method
     */
    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()).close();
        newStage("/fxml/welcome page.fxml");
    }

    /**
     * Handles the action event generated by the login button.
     *
     * @throws IOException check newStage method
     * @throws AppException If an error occurs during login or data retrieval.
     */
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
                user = userManager.searchByUsername(usernameTextField.getText());
                newStage("/fxml/home.fxml");
                ((Stage) loginButton.getScene().getWindow()).close();
            }
        }
    }

    /**
     * Creates and displays a new window.
     *
     * @param resource The path to the FXML resource file that defines the scene layout.
     * @throws IOException If an I/O error occurs while loading the FXML resource.
     */
    private void newStage(String resource) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Parent root = loader.load();
        if (loader.getController() instanceof WelcomePageController)
            stage.initStyle(StageStyle.UNDECORATED);
        else {
            HomeController controller = loader.getController();
            controller.setUserId(user.getId());
            AppFX.titleAndIcon(stage);
        }
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

}
