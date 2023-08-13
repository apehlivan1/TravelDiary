package ba.unsa.etf.rpr.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @FXML
    void addTripClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addTripBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/trip info.fxml"));
        Parent root = loader.load();
        TripInfoController controller = loader.getController();
        controller.setUserId(userId);
        controller.setDestination(destination);

        stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    @FXML
    void initialize() {
        descriptionText.setEditable(false);
        try {
            populateDetails();
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void populateDetails() throws AppException {
        CategoryManager manager = new CategoryManager();
        nameLabel.setText(destination.getName());
        ratingLabel.setText(String.valueOf(destination.getAverageRating()));
        locationLabel.setText(destination.getLocation());
        descriptionText.setText(destination.getDescription());
        categoryLabel.setText(manager.getCategoryName(destination.getCategoryId()));
    }

}
