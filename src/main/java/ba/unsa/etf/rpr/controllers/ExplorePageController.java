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

public class ExplorePageController {

    private int userId;
    private Destination chosenDestination;

    @FXML
    private Button addCategoryBtn;

    @FXML
    private Button addDestinationBtn;

    @FXML
    private Button detailsBtn;

    @FXML
    private ListView<Category> categoriesList;

    @FXML
    private ListView<Destination> destinationsList;

    private CategoryManager categoryManager = new CategoryManager();
    private DestinationManager destinationManager = new DestinationManager();

    public ExplorePageController(int userId) {
        this.userId = userId;
    }

    @FXML
    void addCategoryClicked(ActionEvent event) throws IOException {
        newStage("/fxml/add category.fxml",null);
    }

    @FXML
    void addDestinationClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addDestinationBtn.getScene().getWindow();
        stage.close();
        newStage("/fxml/add destination.fxml", null);
    }

    @FXML
    void viewDetailsClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) detailsBtn.getScene().getWindow();
        stage.close();
        newStage("/fxml/details.fxml", new DetailsController(userId, chosenDestination));
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

    private void refreshCategories() {
        try {
            categoriesList.setItems(FXCollections.observableList(categoryManager.getAll()));
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void newStage(String resource, Object controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
        if (controller != null)
            fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

}
