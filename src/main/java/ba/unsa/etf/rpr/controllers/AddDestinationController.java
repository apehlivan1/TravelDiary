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
        import javafx.scene.control.*;
        import javafx.stage.Stage;

        import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddDestinationController {

    private int userId;

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

    public void setUserId(int userId) {
        this.userId = userId;
    }
    @FXML
    void newCategoryClicked(ActionEvent event) throws IOException {
        newStage(null, "/fxml/add category.fxml");
    }

    @FXML
    void saveClicked(ActionEvent event) throws IOException {
        try {
            DestinationManager manager = new DestinationManager();
            Destination destination = new Destination(
                    0, nameTextField.getText(), locationTextField.getText(),
                    descriptionTextField.getText(), categoryChoice.getValue().getId(), Integer.parseInt(ratingTextField.getText())
            );
            destination = manager.add(destination);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/explore page.fxml"));
            Parent root = loader.load();
            ExplorePageController controller = loader.getController();
            controller.setUserId(userId);
            newStage(root,null);
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    void cancelClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/explore page.fxml"));
        Parent root = loader.load();
        ExplorePageController controller = loader.getController();
        controller.setUserId(userId);
        newStage(root,null);
    }

    @FXML
    void initialize() {
        try {
            categoryChoice.setItems(FXCollections.observableList(categoryManager.getAll()));
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void newStage(Parent root, String resource) throws IOException {
        if (resource != null)
            root = FXMLLoader.load(getClass().getResource(resource));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

}
