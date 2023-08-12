package ba.unsa.etf.rpr.controllers;

import java.io.IOException;
import java.util.List;

import ba.unsa.etf.rpr.business.DestinationManager;
import ba.unsa.etf.rpr.business.TripManager;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class TripInfoController {
    private int userId = -1;

    private Trip trip;

    private Destination destination;

    private TripManager tripManager = new TripManager();

    private DestinationManager destinationManagermanager = new DestinationManager();

    private String originalNote;

    private int originalRating;

    @FXML
    private ChoiceBox<Integer> ratingChoiceBox;

    @FXML
    private TextArea note;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    public TripInfoController(Trip trip, Destination destination) {
        this.trip = trip;
        this.destination = destination;
        originalNote = trip.getNote();
        originalRating = trip.getRating();
    }

    public TripInfoController(int userId, int destinationId) {
        this.userId = userId;
        trip = new Trip(userId, destinationId);
    }

    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()).close();
        openHome();
    }

    @FXML
    void saveClicked(ActionEvent event) throws IOException {
        String updatedNote = note.getText();
        trip.setNote(updatedNote);
        trip.setRating(ratingChoiceBox.getValue());

        try {
            // if userId = -1 --> update;
            // else           --> add new Trip
            if (userId == -1) {
                tripManager.update(trip);
                //update destination.averageRating
                double average = averageRating(tripManager.getAllRatings(destination.getId()));
                destination.setAverageRating(average);
                destinationManagermanager.update(destination);
            } else tripManager.add(trip);
            ((Stage) saveButton.getScene().getWindow()).close();
            openHome();
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    void initialize() {
        // if userId = -1 --> update;
        if (userId == -1) {
            note.setText(originalNote);
            ratingChoiceBox.setValue(originalRating);
        }
        ratingChoiceBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    private void openHome() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    private double averageRating(List<Double> ratings) {
        double sum = 0;
        for (Double rating: ratings) sum += rating;
        return sum/ratings.size();
    }

}
