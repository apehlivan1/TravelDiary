package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddCategoryController {


    @FXML
    private Button addButton;

    @FXML
    private Button addDestinationBtn;

    @FXML
    private TextField categoryNameTextField;

    @FXML
    void addClicked(ActionEvent event) {

    }

    @FXML
    void addDestinationClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addDestinationBtn.getScene().getWindow();
        stage.close();

        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/add destination.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'add category.fxml'.";
        assert addDestinationBtn != null : "fx:id=\"addDestinationBtn\" was not injected: check your FXML file 'add category.fxml'.";
        assert categoryNameTextField != null : "fx:id=\"categoryNameTextField\" was not injected: check your FXML file 'add category.fxml'.";

    }

}
