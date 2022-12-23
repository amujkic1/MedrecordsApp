package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.AfterLoginController;
import ba.unsa.etf.rpr.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainFX extends Application {
    private static Stage stg;
    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;

        /*Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/sample.fxml"));
        stage.setTitle("medrecords");
        stage.setScene(new Scene(root,600,400));
        stage.show();*/

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        LoginController loginController = new LoginController();
        fxmlLoader.setController(loginController);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Med-records");
        stage.show();
    }

    /*public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }*/

    public static void main(String[] args) {
        launch();
    }

}
