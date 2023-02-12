package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class AppFX extends Application {
    public static Stage stg;
    @Override
    public void start(Stage stage) throws Exception {
        stg=stage;
        stg.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        stage.setTitle("medrecords");
        stage.getIcons().add(new Image(AppFX.class.getResourceAsStream("/img/main_icon.png")));
        stage.setScene(new Scene(root,USE_PREF_SIZE,USE_PREF_SIZE));
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
