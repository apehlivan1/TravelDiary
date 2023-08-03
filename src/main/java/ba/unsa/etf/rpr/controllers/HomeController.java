package ba.unsa.etf.rpr.controllers;

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
        import javafx.scene.control.*;

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
    private Button exploreBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTextField;

    public HomeController(int userId) {
        this.userId = userId;
    }

    @FXML
    void exploreBtnClicked(ActionEvent event) {

    }

    @FXML
    void searchClicked(ActionEvent event) {

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

