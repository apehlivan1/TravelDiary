package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    void addDestinationClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'add category.fxml'.";
        assert addDestinationBtn != null : "fx:id=\"addDestinationBtn\" was not injected: check your FXML file 'add category.fxml'.";
        assert categoryNameTextField != null : "fx:id=\"categoryNameTextField\" was not injected: check your FXML file 'add category.fxml'.";

    }

}
