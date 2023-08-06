package ba.unsa.etf.rpr.controllers;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TextField;

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
    void cancelClicked(ActionEvent event) {

    }

    @FXML
    void newCategoryClicked(ActionEvent event) {

    }

    @FXML
    void saveClicked(ActionEvent event) {

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
