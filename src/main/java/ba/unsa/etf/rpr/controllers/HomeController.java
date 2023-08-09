package ba.unsa.etf.rpr.controllers;

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

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    private Trip chosenTrip;
    private List<Trip> tripsList;
    private TripManager tripManager = new TripManager();
    private Destination chosenDestination;
    private DestinationManager destinationManager = new DestinationManager();
    @FXML
    private int userId;

    @FXML
    private ListView<Destination> destinationsList;

    @FXML
    private Label note;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button exploreBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTextField;

    public HomeController(int userId) {
        this.userId = userId;
    }

    @FXML
    void deleteClicked(ActionEvent event) {
        try {
            tripManager.delete(chosenTrip.getId());
            note.setText(chosenDestination.getName() + " deleted.");
            nothingChosenState();
        } catch (AppException e) {
            note.setText("The removal of the selected trip was unsuccessful.");
        }
    }

    @FXML
    void exploreBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) exploreBtn.getScene().getWindow();
        stage.close();
        newStage("/fxml/explore page.fxml", new ExplorePageController(userId));
    }

    @FXML
    void searchClicked(ActionEvent event) throws AppException {
        List<Destination> destinations = destinationManager.search(searchTextField.getText());
        destinationsList.getItems().addAll(destinations);
    }

    @FXML
    void editButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.close();
        newStage("/fxml/edit.fxml", new EditController(chosenTrip.getId()));
    }


    @FXML
    void initialize() {
        try {
            nothingChosenState();
            refreshList();
            destinationsList.getSelectionModel().selectedItemProperty().addListener((observableValue, destination, t1) -> {
                chosenDestination = destinationsList.getSelectionModel().getSelectedItem();
                for (Trip trip: tripsList) {
                    if (trip.getDestinationId() == chosenDestination.getId()) {
                        chosenTrip = trip;
                        break;
                    }
                }
                note.setText(chosenTrip.toString());
                editButton.setVisible(true);
                deleteButton.setVisible(true);
            });
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void refreshList() throws AppException {
        tripsList = tripManager.searchByUser(userId);
        for (Trip trip: tripsList) {
            int destinationId = trip.getDestinationId();
            Destination destination = destinationManager.getById(destinationId);
            destinationsList.getItems().add(destination);
        }
    }

    private void nothingChosenState() {
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        chosenDestination = null;
        chosenTrip = null;
    }

    private void newStage(String resource, Object controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
        if (controller != null) fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }
}
