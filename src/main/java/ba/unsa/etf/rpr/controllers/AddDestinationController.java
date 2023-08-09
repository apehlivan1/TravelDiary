package ba.unsa.etf.rpr.controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

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
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

        import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddDestinationController {

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Category> categoryChoice; //try with <String>

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button newCategoryBtn;

    @FXML
    private TextField ratingTextField;

    @FXML
    private Button saveButton;

    CategoryManager categoryManager = new CategoryManager();

    @FXML
    void newCategoryClicked(ActionEvent event) throws IOException {
        newStage("/fxml/add category.fxml");
    }

    @FXML
    void saveClicked(ActionEvent event) throws AppException, IOException {
        DestinationManager manager = new DestinationManager();
        Destination destination = new Destination(
                0, nameTextField.getText(), locationTextField.getText(),
                descriptionTextField.getText(), categoryChoice.getValue().getId(), Integer.parseInt(ratingTextField.getText())
        );
        destination = manager.add(destination);
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
        newStage("/fxml/explore page.fxml");
    }

    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        newStage("/fxml/explore page.fxml");
    }

    @FXML
    void initialize() {
        try {
            categoryChoice.setItems(FXCollections.observableList(categoryManager.getAll()));
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    private void newStage(String resource) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

}
