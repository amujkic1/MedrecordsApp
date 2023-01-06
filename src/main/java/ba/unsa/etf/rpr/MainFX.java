package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import ba.unsa.etf.rpr.domain.Patients;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class MainFX extends Application {
    public static Stage stg;
    private Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        stg=stage;
        stg.setResizable(false);
        //stage.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        stage.setTitle("medrecords");
        stage.setScene(new Scene(root,USE_PREF_SIZE,USE_PREF_SIZE));
        stage.show();


        /*Patients patient = new Patients(123, "A", "B",
                "C", "D", "E", 44,
                "f", 345, "asd",
                "www", 7896);

        p.add(patient);*/

        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        LoginController loginController = new LoginController();
        fxmlLoader.setController(loginController);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Med-records");
        stage.show();*/
    }


    public static void main(String[] args) {
        launch(args);
    }

    /*public void switchScenes(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }*/
}
