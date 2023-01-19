package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.RecordsDaoImpl;
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
    @FXML
    private Label blood;
    @FXML
    private Label height;
    @FXML
    private Label weight;
    private Patients patient;
    private Records rec;
    private RecordsDaoImpl tmp;
    private int record_id;
    public PatientRecordController(Patients patient, Records rec) {
        this.patient = patient;
        this.rec = rec;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(patient.toString());
        blood.setText(rec.getBlood());
        height.setText(rec.getHeight()+"cm");
        weight.setText(rec.getWeight()+"kq");

    }
}
