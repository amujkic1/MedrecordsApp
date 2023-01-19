package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.MainFX;
import ba.unsa.etf.rpr.dao.DoctorsDaoImpl;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import ba.unsa.etf.rpr.dao.RecordsDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class LoginController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label wronglogin;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ChoiceBox<String> choice;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {
        System.out.println("login");
        validateLogin();
    }

    public void validateLogin() throws IOException {

        //popravi uslove za validaciju

        DoctorsDaoImpl doc = new DoctorsDaoImpl();
        PatientsDaoImpl pt = new PatientsDaoImpl();
        String user = username.getText();

        if(choice.getValue().equals("Doctor")) {
            if (doc.searchByUsername(username.getText()) != null
                    && doc.searchByUsername(username.getText()).getPassword().equals(password.getText())) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/afterdoctorlogin.fxml"));
                AfterDoctorLoginController afterDoctorLoginController = new AfterDoctorLoginController(username.getText(),
                        doc.searchByUsername(username.getText()).getId());
                fxmlLoader.setController(afterDoctorLoginController);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
                stage.show();
                Stage s = (Stage) username.getScene().getWindow();
                s.close();
            }
            else{
                username.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(username).play();
                password.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(password).play();
            }
        }

        if(choice.getValue().equals("Patient")) {
            if (pt.searchByUsername(username.getText()) != null
                    && pt.searchByUsername(username.getText()).getPassword().equals(password.getText())) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/afterpatientlogin.fxml"));
                AfterPatientLoginController afterPatientLoginController = new AfterPatientLoginController(username.getText());
                fxmlLoader.setController(afterPatientLoginController);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
                stage.show();
                Stage s = (Stage) username.getScene().getWindow();
                s.close();
            }
            else{
                username.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(username).play();
                password.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(password).play();
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        choice.getItems().addAll("Doctor", "Patient");
    }

}