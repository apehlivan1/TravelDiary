package ba.unsa.etf.rpr.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class WelcomePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInBtn;

    @FXML
    private Button registerBtn;


    @FXML
    void btnRegisterClicked(ActionEvent event) throws IOException {
        newStage("/fxml/register.fxml");
        ((Stage) registerBtn.getScene().getWindow()).close();
    }

    @FXML
    void btnLogInClicked(ActionEvent event) throws IOException {
        newStage("/fxml/login.fxml");
        ((Stage) logInBtn.getScene().getWindow()).close();
    }

    @FXML
    void initialize() {

    }

    private void newStage(String resource) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}