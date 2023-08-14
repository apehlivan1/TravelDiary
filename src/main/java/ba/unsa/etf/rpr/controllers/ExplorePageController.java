package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.DestinationManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for 'Explore Page'.
 */
public class ExplorePageController {

    private int userId;
    private Destination chosenDestination;

    @FXML
    private Button addDestinationBtn;

    @FXML
    private Button detailsBtn;

    @FXML
    private ListView<Category> categoriesList;

    @FXML
    private ListView<Destination> destinationsList;

    private final CategoryManager categoryManager = new CategoryManager();
    private final DestinationManager destinationManager = new DestinationManager();

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Opens a new window for adding a destination.
     */
    @FXML
    void addDestinationClicked(ActionEvent event) throws IOException {
        ((Stage) addDestinationBtn.getScene().getWindow()).close();
        newStage(null, "/fxml/add destination.fxml");
    }

    /**
     * For viewing detailed information about the chosen destination
     */
    @FXML
    void viewDetailsClicked(ActionEvent event) throws IOException {
        ((Stage) detailsBtn.getScene().getWindow()).close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/details.fxml"));
        Parent root = loader.load();
        DetailsController controller = loader.getController();
        controller.setUserId(userId);
        controller.setDestination(chosenDestination);
        newStage(root,null);
    }

    @FXML
    void initialize() {
        try {
            detailsBtn.setVisible(false);
            refreshCategories();
            Category category = categoriesList.getSelectionModel().getSelectedItem();
            if (category != null) {
                List<Destination> resultList = destinationManager.searchByCategory(category.getId());
                destinationsList.setItems(FXCollections.observableList(resultList));
            }

            destinationsList.getSelectionModel().selectedItemProperty().addListener((observableValue, destination, t1) -> {
                chosenDestination = destinationsList.getSelectionModel().getSelectedItem();
                detailsBtn.setVisible(true);
            });
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Refreshes the categories list.
     */
    private void refreshCategories() {
        try {
            categoriesList.setItems(FXCollections.observableList(categoryManager.getAll()));
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * @param root The root content (Parent) to be displayed in the new stage.
     *             If this param is null, passing data to another controller is not necessary
     * @param resource The path to the FXML resource file that defines the scene layout.
     *                 If this param is null, it implies that the root content is preloaded and data is provided to a controller.
     * @throws IOException If an I/O error occurs while loading the resource
     */
    private void newStage(Parent root, String resource) throws IOException {
        if (resource != null)
            root = FXMLLoader.load(getClass().getResource(resource));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

}
