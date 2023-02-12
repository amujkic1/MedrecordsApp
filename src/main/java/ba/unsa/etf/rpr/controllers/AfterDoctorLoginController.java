package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.AppointmentManager;
import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class AfterDoctorLoginController implements Initializable {
    @FXML
    private Button search;
    @FXML
    private TextField txtfield;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu file;
    @FXML
    private Menu edit;
    @FXML
    private Menu help;
    @FXML
    private Button addbutton;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem _new;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem about;
    @FXML
    private MenuItem delete;
    @FXML
    private Button logout;
    @FXML
    private Label warning;
    public ListView patientList;
    String DocUsername;
    int doctor_id;
    public AfterDoctorLoginController(String DocUsername, int doctor_id) {
        this.DocUsername = DocUsername;
        this.doctor_id = doctor_id;
    }

    public void searchByUser() throws MyException, IOException {
        if(txtfield.getText().equals("")){ warning.setText("You have to input data for search"); return; }

            PatientManager pat = new PatientManager();
            DoctorManager dom = new DoctorManager();
            RecordManager rm = new RecordManager();
            Patients patient = pat.findByUsername(txtfield.getText());
            if(patient==null){
                warning.setText("This name does not exist in the base");
                return;
            }
            Doctors doctor = dom.getById(patient.getDoctor_id());
            Records rec = rm.getById(patient.getRecord_id());
            PatientRecordController patientRecordController = new PatientRecordController(patient, rec, doctor, "d");
            newWindow("/fxml/patientrecord.fxml", patientRecordController, 1, 0);

    }

    public void logOut() throws IOException {
        newWindow("/fxml/sample.fxml", null, 1, 0);
    }

    public void addPatient() throws IOException {

        AddRecordController apc = new AddRecordController(doctor_id);
        newWindow("/fxml/addrecord.fxml", apc, 0, 0);

    }

    public void openRecord() throws IOException {
        OpenRecordController openRecordController = new OpenRecordController();
        newWindow("/fxml/open.fxml", openRecordController, 0, 0);
    }

    public void deleteRecord() throws IOException {

        DeleteRecordController deleteController = new DeleteRecordController();
        newWindow("/fxml/delete.fxml", deleteController, 0, 0);

    }

    public void help() throws IOException {
        HelpController helpController = new HelpController();
        newWindow("/fxml/help.fxml", helpController, 0, 0);
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
        Stage s = (Stage) txtfield.getScene().getWindow();
        if(close==1)
            s.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientManager pdao = new PatientManager();
        DoctorManager ddao = new DoctorManager();
        AppointmentManager appointmentManager = new AppointmentManager();

        try {
            patientList.setItems(appointmentManager.haveAppointmentAtDoctor(doctor_id));
            //patientList.setItems(pdao.allPatients(ddao.searchByUsername(DocUsername).getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        patientList.setOnMouseClicked(event -> {
            String selected = (String) patientList.getSelectionModel().getSelectedItem();
            Records rec = new Records();
            RecordManager rdi = new RecordManager();
            PatientManager patientManager = new PatientManager();

            try {
                rec = rdi.findUserRecord(selected);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            DoctorManager doctor = new DoctorManager();
            PatientRecordController patientRecordController = new PatientRecordController(patientManager.findByUsername(selected),
                    rec, doctor.searchByUsername(DocUsername), "d");
            try {
                newWindow("/fxml/patientrecord.fxml", patientRecordController, 1, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
