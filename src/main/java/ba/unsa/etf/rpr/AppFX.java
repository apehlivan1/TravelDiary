package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AppFX extends Application {

    public static void titleAndIcon(Stage stage) {
        stage.setTitle("TravelDiary");
        stage.getIcons().add(new Image(AppFX.class.getResourceAsStream("/images/logoSymbol.png")));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/welcome page.fxml"));
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        titleAndIcon(primaryStage);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
