package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientRecordController implements Initializable {
    @FXML
    private Label name;
    private Patients patient;
    private Records rec;

    public PatientRecordController(Patients patient) {
        this.patient = patient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(patient.toString());
    }
}
