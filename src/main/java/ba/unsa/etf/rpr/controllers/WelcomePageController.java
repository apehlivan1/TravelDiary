package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller class for the welcome page of the application.
 *
 * @author Almedina Pehlivan
 */
public class WelcomePageController {

    @FXML
    private Button logInBtn;

    @FXML
    private Button registerBtn;

    /**
     * Handles the event when the register button is clicked.
     * Opens the registration screen and closes the current welcome page.
     *
     * @param event The action event triggered by clicking the register button.
     * @throws IOException newStage method exception
     */
    @FXML
    void btnRegisterClicked(ActionEvent event) throws IOException {
        newStage("/fxml/register.fxml");
        ((Stage) registerBtn.getScene().getWindow()).close();
    }

    /**
     * Handles the event when the login button is clicked.
     * Opens the login screen and closes the current welcome page.
     *
     * @param event The action event triggered by clicking the login button.
     * @throws IOException If an I/O error occurs while loading the login screen.
     */
    @FXML
    void btnLogInClicked(ActionEvent event) throws IOException {
        newStage("/fxml/login.fxml");
        ((Stage) logInBtn.getScene().getWindow()).close();
    }

    /**
     * Opens a new scene with the specified FXML resource.
     *
     * @param resource The path to the FXML resource file to be loaded.
     * @throws IOException If an I/O error occurs while loading the FXML resource.
     */
    private void newStage(String resource) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        AppFX.titleAndIcon(stage);
        stage.show();
    }

}