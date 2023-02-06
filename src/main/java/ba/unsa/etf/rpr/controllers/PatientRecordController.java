package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.RecordsDaoImpl;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class PatientRecordController implements Initializable {
    @FXML
    private Label name;
    @FXML
    private Label blood;
    @FXML
    private Label height;
    @FXML
    private Label weight;
    @FXML
    private Button tbBack;
    private Patients patient;
    private Records rec;
    private RecordsDaoImpl tmp;
    private int record_id;
    private Doctors doctor;
    private String who;

    public PatientRecordController(Patients patient, Records rec, Doctors doc, String who) {
        this.patient = patient;
        this.rec = rec;
        this.doctor = doc;
        this.who = who;
    }


    public void back() throws IOException {
        if(who.equals("d")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/afterdoctorlogin.fxml"));
            AfterDoctorLoginController afterDoctorLoginController = new AfterDoctorLoginController(doctor.getUsername(),
                    doctor.getId());
            fxmlLoader.setController(afterDoctorLoginController);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
            stage.show();
            Stage s = (Stage) name.getScene().getWindow();
            s.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
            LoginController loginController = new LoginController();
            //fxmlLoader.setController(loginController);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
            stage.show();
            Stage s = (Stage) name.getScene().getWindow();
            s.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(patient.toString());
        blood.setText(rec.getBlood());
        height.setText(rec.getHeight()+"cm");
        weight.setText(rec.getWeight()+"kq");

    }

}
