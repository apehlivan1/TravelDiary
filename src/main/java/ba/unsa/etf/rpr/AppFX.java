package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.WelcomePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AppFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/welcome-page.fxml"));
        WelcomePage welcomePageController = new WelcomePage();
        fxmlLoader.setController(welcomePageController);
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
