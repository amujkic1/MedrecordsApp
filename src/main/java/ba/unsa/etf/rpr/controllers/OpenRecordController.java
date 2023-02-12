package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class OpenRecordController {
    @FXML
    private TextField username;
    @FXML
    private Button open;
    @FXML
    private Button cancel;
    @FXML
    private Label error;

    public OpenRecordController(){}

    public void open() throws MyException, IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();

        if(username.getText().equals("")){ error.setText("You have to input data for search"); return; }

        PatientManager patientManager = new PatientManager();
        DoctorManager doctorManager = new DoctorManager();
        RecordManager recordManager = new RecordManager();
        Patients patient = patientManager.findByUsername(username.getText());
        if(patient==null){
            error.setText("This name does not exist in the base");
            return;
        }
        Doctors doctor = doctorManager.getById(patient.getDoctor_id());
        Records rec = recordManager.getById(patient.getRecord_id());

        PatientRecordController patientRecordController = new PatientRecordController(patient, rec, doctor, "d");
        newWindow("/fxml/patientrecord.fxml", patientRecordController, 1, 0);


        stage.close();
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void newWindow(String file, Object o, int close, int resizable) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(file));
        if(o != null)
            fxmlLoader.setController(o);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        if(resizable==0){
            stage.setResizable(false);
        }
        stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        stage.show();
        Stage s = (Stage) username.getScene().getWindow();
        if(close==1)
            s.close();
    }

}