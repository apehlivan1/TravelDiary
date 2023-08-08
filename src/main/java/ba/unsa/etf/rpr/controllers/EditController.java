package ba.unsa.etf.rpr.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ba.unsa.etf.rpr.business.DestinationManager;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EditController {

    private Destination destination;

    @FXML
    private TextArea note;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    public EditController(int destinationId) {
        DestinationManager manager = new DestinationManager();
        try {
            destination = manager.getById(destinationId);
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
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'edit.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'edit.fxml'.";

    }

}
