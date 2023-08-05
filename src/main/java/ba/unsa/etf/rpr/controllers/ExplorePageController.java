package ba.unsa.etf.rpr.controllers;


        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.ListView;

public class ExplorePageController {

    @FXML
    private Button addCategoryBtn;

    @FXML
    private Button addDestinationBtn;

    @FXML
    private ListView<?> categoriesList;

    @FXML
    private ListView<?> destinationsList;

    @FXML
    void addCategoryClicked(ActionEvent event) {

    }

    @FXML
    void addDestinationClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert addCategoryBtn != null : "fx:id=\"addCategoryBtn\" was not injected: check your FXML file 'explore page.fxml'.";
        assert addDestinationBtn != null : "fx:id=\"addDestinationBtn\" was not injected: check your FXML file 'explore page.fxml'.";
        assert categoriesList != null : "fx:id=\"categoriesList\" was not injected: check your FXML file 'explore page.fxml'.";
        assert destinationsList != null : "fx:id=\"destinationsList\" was not injected: check your FXML file 'explore page.fxml'.";

    }

}
