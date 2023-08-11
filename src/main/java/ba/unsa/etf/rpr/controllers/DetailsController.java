package ba.unsa.etf.rpr.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ba.unsa.etf.rpr.domain.Destination;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class DetailsController {

    private int userId;
    private Destination destination;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void initialize() {
        assert categoryLabel != null : "fx:id=\"categoryLabel\" was not injected: check your FXML file 'details.fxml'.";
        assert descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'details.fxml'.";
        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'details.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'details.fxml'.";
        assert ratingLabel != null : "fx:id=\"ratingLabel\" was not injected: check your FXML file 'details.fxml'.";

    }

}
