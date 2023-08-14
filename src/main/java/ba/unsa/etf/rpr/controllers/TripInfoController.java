package ba.unsa.etf.rpr.controllers;

import java.io.IOException;
import java.util.List;

import ba.unsa.etf.rpr.business.DestinationManager;
import ba.unsa.etf.rpr.business.TripManager;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller class for adding destinations to saved trips and updating saved notes.
 */
public class TripInfoController {

    private int userId = -1;

    private Trip trip;

    private Destination destination;

    private final TripManager tripManager = new TripManager();

    private final DestinationManager destinationManager = new DestinationManager();

    @FXML
    private ChoiceBox<Integer> ratingChoiceBox;

    @FXML
    private TextArea note;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    /**
     * Cancel button event handler
     */
    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()).close();
        openHome();
    }

    /**
     * Save button event handler
     */
    @FXML
    void saveClicked(ActionEvent event) throws IOException {
        if (userId != -1) trip = new Trip(userId, destination.getId());
        String updatedNote = note.getText();
        trip.setNote(updatedNote);
        trip.setRating(ratingChoiceBox.getValue());

        try {
            // if userId = -1 --> update;
            // else           --> add new Trip
            if (userId == -1) {
                tripManager.update(trip);
                //update destination.averageRating
                destination.setAverageRating(averageRating());
                destinationManager.update(destination);
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
            String originalNote = trip.getNote();
            int originalRating = trip.getRating();
            note.setText(originalNote);
            ratingChoiceBox.setValue(originalRating);
        }
        ratingChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
    }

    /**
     * Returns to "Home Page"
     */
    private void openHome() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    /**
     * Calculates the average rating from a list of ratings.
     * @return average rating
     */
    private double averageRating() throws AppException {
        List<Double> ratings = tripManager.getAllRatings(destination.getId());
        double sum = 0;
        for (Double rating: ratings) sum += rating;
        return sum/ratings.size();
    }

}
