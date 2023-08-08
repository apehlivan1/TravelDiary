package ba.unsa.etf.rpr.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ba.unsa.etf.rpr.business.DestinationManager;
import ba.unsa.etf.rpr.business.TripManager;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EditController {

    private Trip trip;

    @FXML
    private TextArea note;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    public EditController(int tripId) {
        TripManager manager = new TripManager();
        try {
            trip = manager.getById(tripId);
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cancelClicked(ActionEvent event) {

    }

    @FXML
    void saveClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
