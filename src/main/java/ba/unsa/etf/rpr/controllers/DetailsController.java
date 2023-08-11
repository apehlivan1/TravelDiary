package ba.unsa.etf.rpr.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ba.unsa.etf.rpr.domain.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class DetailsController {

    private int userId;
    private Destination destination;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addTripBtn;

    @FXML
    private Label categoryLabel;

    @FXML
    private TextArea descriptionText;

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ratingLabel;

    public DetailsController(int userId, Destination destination) {
        this.userId = userId;
        this.destination = destination;
    }

    @FXML
    void addTripClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addTripBtn.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/trip info.fxml"));
        fxmlLoader.setController(new TripInfoController(userId, destination.getId()));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    @FXML
    void initialize() {
        assert categoryLabel != null : "fx:id=\"categoryLabel\" was not injected: check your FXML file 'details.fxml'.";
        assert descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'details.fxml'.";
        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'details.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'details.fxml'.";
        assert ratingLabel != null : "fx:id=\"ratingLabel\" was not injected: check your FXML file 'details.fxml'.";

    }

}
