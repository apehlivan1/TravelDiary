package ba.unsa.etf.rpr.controllers;

import java.io.IOException;
import java.util.List;

import ba.unsa.etf.rpr.AppFX;
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

    private Boolean isUpdate;
    private int userId;
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

    public TripInfoController(int userId, Trip trip, Destination destination) {
        this.userId = userId;
        this.destination = destination;
        this.trip = trip;
        isUpdate = true;
    }

    public TripInfoController(int userId, Destination destination) {
        this.userId = userId;
        this.destination = destination;
        trip = new Trip(userId, destination.getId());
        isUpdate = false;
    }

    /**
     * Cancel button event handler
     * Returns to "Home page" or "Destination details" depending on previous page
     */
    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()).close();
        if (!isUpdate)
            newStage("/fxml/details.fxml", new DetailsController(userId, destination));
        else
            newStage("/fxml/home.fxml", new HomeController(userId));
    }

    /**
     * Save button event handler
     */
    @FXML
    void saveClicked(ActionEvent event) throws IOException {
        String updatedNote = note.getText();
        trip.setNote(updatedNote);
        trip.setRating(ratingChoiceBox.getValue());
        try {
            if (isUpdate) {
                tripManager.update(trip);
                //update destination.averageRating
                destination.setAverageRating(averageRating());
                destinationManager.update(destination);
            } else tripManager.add(trip);

            ((Stage) saveButton.getScene().getWindow()).close();
            newStage("/fxml/home.fxml", new HomeController(userId));
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    void initialize() {
        if (isUpdate) {
            String originalNote = trip.getNote();
            int originalRating = trip.getRating();
            note.setText(originalNote);
            ratingChoiceBox.setValue(originalRating);
        }
        ratingChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
    }

    /**
     * Opens new window
     */
    private void newStage(String resource, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        AppFX.titleAndIcon(stage);
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
