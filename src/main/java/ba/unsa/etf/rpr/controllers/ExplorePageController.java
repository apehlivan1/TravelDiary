package ba.unsa.etf.rpr.controllers;


        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.ListView;
        import javafx.stage.Stage;

        import java.io.IOException;

        import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
    void addCategoryClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/add category.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
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
        assert addCategoryBtn != null : "fx:id=\"addCategoryBtn\" was not injected: check your FXML file 'explore page.fxml'.";
        assert addDestinationBtn != null : "fx:id=\"addDestinationBtn\" was not injected: check your FXML file 'explore page.fxml'.";
        assert categoriesList != null : "fx:id=\"categoriesList\" was not injected: check your FXML file 'explore page.fxml'.";
        assert destinationsList != null : "fx:id=\"destinationsList\" was not injected: check your FXML file 'explore page.fxml'.";

    }

}
