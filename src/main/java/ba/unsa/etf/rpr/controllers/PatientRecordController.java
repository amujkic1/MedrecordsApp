package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.business.AppointmentManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.RecordsDaoImpl;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
    @FXML
    private Label appointmentlabel;
    @FXML
    private ListView<String> list;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button update;
    @FXML
    private Button addp;
    @FXML
    private Label error;
    private Patients patient;
    private Records rec;
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
            AfterDoctorLoginController afterDoctorLoginController = new AfterDoctorLoginController(doctor.getUsername(),
                    doctor.getId());
            newWindow("/fxml/afterdoctorlogin.fxml", afterDoctorLoginController, 1, 0);
        }else{
            newWindow("/fxml/sample.fxml", null, 1, 0);
        }
    }

    public void fill(){
        if (choiceBox.getValue().equals("Allergies")) {
            RecordManager rm = new RecordManager();
            try {
                list.setItems(rm.allergies(patient.getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (choiceBox.getValue().equals("Prescriptions")) {
            RecordManager rm = new RecordManager();
            try {
                list.setItems(rm.prescriptions(patient.getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void createAppointment() throws IOException {

        CreateAppointmentController createAppointmentController = new CreateAppointmentController(patient.getId());
        newWindow("/fxml/createappointment.fxml", createAppointmentController, 0, 0);

    }
    public void addDiagnosis() throws IOException {
        if(who.equals("p")){ error.setText("This is not allowed for patients"); return; }
        RecordUpdateController recordUpdateController = new RecordUpdateController(rec.getId(), "diagnosis", who);
        newWindow("/fxml/newdiagnosis.fxml", recordUpdateController, 0, 0);
    }

    public void addPrescription() throws IOException {
        if(who.equals("p")){ error.setText("This is not allowed for patients"); return; }
        RecordUpdateController recordUpdateController = new RecordUpdateController(rec.getId(), "prescription", who);
        newWindow("/fxml/newdiagnosis.fxml", recordUpdateController, 0, 0);
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
        Stage s = (Stage) addp.getScene().getWindow();
        if(close==1)
            s.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(patient.toString());
        blood.setText(rec.getBlood());
        height.setText(rec.getHeight() + "cm");
        weight.setText(rec.getWeight() + "kq");

        choiceBox.getItems().addAll("Allergies", "Prescriptions");

        choiceBox.setOnAction(e -> {
            fill();
        });

        if(who.equals("p")){
            AppointmentManager am = new AppointmentManager();
            try {
                Appointments app = am.searchByPatient(patient.getId());
                if(app != null){
                    appointmentlabel.setText("You have an appointment on " + app.getDate());
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

}