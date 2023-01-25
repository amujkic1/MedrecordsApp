package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class LoginController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label wronglogin;
    @FXML
    private Label user;
    @FXML
    private Label pw;
    @FXML
    private Label role;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ChoiceBox<String> choice;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public LoginController(){}
    public void login(ActionEvent event) throws IOException {
        System.out.println("login");
        validateLogin();
    }

    public void validateLogin() throws IOException {

        DoctorManager doc = new DoctorManager();
        PatientManager pt = new PatientManager();

        if(choice.getValue().equals("Doctor")) {

            String code = doc.validateDoctorLogin(username.getText(), password.getText());
            if(code.equals("empty user field") || code.equals("username does not exist")){
                username.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(username).play();
            }

            if(code.equals("empty password field") || code.equals("wrong password")){
                password.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(password).play();
            }

            if(code.equals("ok")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/afterdoctorlogin.fxml"));
                AfterDoctorLoginController afterDoctorLoginController = new AfterDoctorLoginController(username.getText(),
                        doc.searchByUsername(username.getText()).getId());
                fxmlLoader.setController(afterDoctorLoginController);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
                stage.show();
                Stage s = new Stage();
                s = (Stage) username.getScene().getWindow();
                s.close();
            }
        }

        if(choice.getValue().equals("Patient")) {

            String code = pt.validatePatientLogin(username.getText(), password.getText());
            if(code.equals("empty user field") || code.equals("username does not exist")){
                username.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(username).play();
            }

            if(code.equals("empty password field") || code.equals("wrong password")){
                password.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(password).play();
            }

            if(code.equals("ok")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/patientrecord.fxml"));
                PatientManager pat = new PatientManager();
                DoctorManager dom = new DoctorManager();
                RecordManager rm = new RecordManager();
                Patients patient = pat.findByUsername(username.getText());
                Doctors doctor = dom.getById(patient.getDoctor_id());
                Records rec = rm.getById(patient.getRecord_id());

                PatientRecordController patientRecordController = new PatientRecordController(patient, rec, doctor);
                fxmlLoader.setController(patientRecordController);

                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
                stage.show();
                Stage s = (Stage) username.getScene().getWindow();
                s.close();
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        choice.getItems().addAll("Doctor", "Patient");
        user.setFont(new Font("Bahnschrift SemiBold", 14));
        pw.setFont(new Font("Bahnschrift SemiBold", 14));
        role.setFont(new Font("Bahnschrift SemiBold", 16));
    }

}