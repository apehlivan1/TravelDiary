package ba.unsa.etf.rpr.controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.util.List;
        import java.util.ResourceBundle;

        import ba.unsa.etf.rpr.business.DestinationManager;
        import ba.unsa.etf.rpr.business.TripManager;
        import ba.unsa.etf.rpr.domain.Destination;
        import ba.unsa.etf.rpr.domain.Trip;
        import ba.unsa.etf.rpr.exceptions.AppException;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;

        import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    @FXML
    private int userId;

    @FXML
    private ListView<Destination> destinationsList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label note;

    @FXML
    private Button exploreBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTextField;

    public HomeController(int userId) {
        this.userId = userId;
    }

    @FXML
    void exploreBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) exploreBtn.getScene().getWindow();
        stage.close();

        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/explore page.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();

    }

    @FXML
    void searchClicked(ActionEvent event) throws AppException {
        DestinationManager destinationManager = new DestinationManager();
        List<Destination> destinations = destinationManager.search(searchTextField.getText());
        destinationsList.getItems().addAll(destinations);
    }

    @FXML
    void initialize() {
        try{
            TripManager tripManager = new TripManager();
            List<Trip> tripsList = tripManager.searchByUser(userId);

            DestinationManager destinationManager = new DestinationManager();
            for (Trip trip: tripsList) {
                int destinationId = trip.getDestinationId();
                Destination destination = destinationManager.getById(destinationId);
                destinationsList.getItems().add(destination);
            }

            //destinationsList.getSelectionModel().selectedItemProperty().addListener();
            //destinationsList.getItems().addAll()
        } catch (AppException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

}

