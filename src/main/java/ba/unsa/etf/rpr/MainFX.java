package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class MainFX extends Application {
    public static Stage stg;
    private Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        stg=stage;
        stg.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        stage.setTitle("medrecords");
        stage.getIcons().add(new Image(MainFX.class.getResourceAsStream("/img/main_icon.png")));
        stage.setScene(new Scene(root,USE_PREF_SIZE,USE_PREF_SIZE));
        stage.show();

        /*System.out.println("test");
        Doctors doc = new Doctors(1,"a", "b", "c", "d", "123", 22, "f",
                "e", "d", "d");
        DoctorManager dm = new DoctorManager();
        dm.add(doc);*/

    }


    public static void main(String[] args) {
        launch(args);
    }

}
