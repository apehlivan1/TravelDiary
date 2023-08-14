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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for adding destinations
 * @author Almedina Pehlivan
 */
public class AddDestinationController {

    private int userId;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Category> categoryChoice;

    @FXML
    private ComboBox<Integer> ratingChoice;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button newCategoryBtn;

    @FXML
    private Button saveButton;

    private final CategoryManager categoryManager = new CategoryManager();

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * "Add new category" button event handler
     */
    @FXML
    void newCategoryClicked(ActionEvent event) throws IOException {
        newStage(null, "/fxml/add category.fxml");
    }

    /**
     * Save button event handler
     */
    @FXML
    void saveClicked(ActionEvent event) throws IOException {
        try {
            DestinationManager manager = new DestinationManager();
            Destination destination = new Destination(
                    0, nameTextField.getText(), locationTextField.getText(),
                    descriptionTextField.getText(), categoryChoice.getValue().getId(), ratingChoice.getValue());
            manager.add(destination);
            ((Stage) saveButton.getScene().getWindow()).close();
            openExplorePage();
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Cancel button event handler
     */
    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        ((Stage) cancelButton.getScene().getWindow()) .close();
        openExplorePage();
    }

    @FXML
    void initialize() {
        try {
            categoryChoice.setItems(FXCollections.observableList(categoryManager.getAll()));
            ratingChoice.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Opens "Explore Page"
     */
    private void openExplorePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/explore page.fxml"));
        Parent root = loader.load();
        ExplorePageController controller = loader.getController();
        controller.setUserId(userId);
        newStage(root,null);
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
