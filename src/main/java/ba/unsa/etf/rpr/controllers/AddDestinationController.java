package ba.unsa.etf.rpr.controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;
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
    private ComboBox<?> categoryChoice;

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

    @FXML
    void newCategoryClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) newCategoryBtn.getScene().getWindow();
        stage.close();

        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/add category.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    @FXML
    void saveClicked(ActionEvent event) {

    }

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'add destination.fxml'.";
        assert categoryChoice != null : "fx:id=\"categoryChoice\" was not injected: check your FXML file 'add destination.fxml'.";
        assert descriptionTextField != null : "fx:id=\"descriptionTextField\" was not injected: check your FXML file 'add destination.fxml'.";
        assert locationTextField != null : "fx:id=\"locationTextField\" was not injected: check your FXML file 'add destination.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'add destination.fxml'.";
        assert newCategoryBtn != null : "fx:id=\"newCategoryBtn\" was not injected: check your FXML file 'add destination.fxml'.";
        assert ratingTextField != null : "fx:id=\"ratingTextField\" was not injected: check your FXML file 'add destination.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'add destination.fxml'.";

    }

}
