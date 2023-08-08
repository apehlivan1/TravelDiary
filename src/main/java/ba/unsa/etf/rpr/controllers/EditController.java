package ba.unsa.etf.rpr.controllers;

import java.io.IOException;

import ba.unsa.etf.rpr.business.TripManager;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class EditController {

    private Trip trip;

    private TripManager manager = new TripManager();

    private String originalNote;

    @FXML
    private TextArea note;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    public EditController(int tripId) {
        try {
            trip = manager.getById(tripId);
            originalNote = trip.getNote();
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()).close();
        openHome();
    }

    @FXML
    void saveClicked(ActionEvent event) throws AppException, IOException {
        String updatedNote = note.getText();
        trip.setNote(updatedNote);
        manager.update(trip);
        ((Stage) saveButton.getScene().getWindow()).close();
        openHome();
    }

    @FXML
    void initialize() {
        note.setText(originalNote);
    }

    private void openHome() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

}
