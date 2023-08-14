package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * JavaFX controller for adding categories
 * @author Almedina Pehlivan
 */
public class AddCategoryController {

    @FXML
    private Button addButton;

    @FXML
    private TextField categoryNameTextField;

    /**
     * Add button event handler
     */
    @FXML
    void addClicked(ActionEvent event) {
        try {
            Category c = new Category();
            c.setName(categoryNameTextField.getText());
            CategoryManager manager = new CategoryManager();
            manager.add(c);
            categoryNameTextField.setText("");
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
