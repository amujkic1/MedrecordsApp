package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class AfterDoctorLoginController implements Initializable {
    @FXML
    private BorderPane bp;
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
    private Stage stage;
    private Scene scene;
    private Parent root;
    String DocUsername;
    int doctor_id;
    public AfterDoctorLoginController(String DocUsername, int doctor_id) {
        this.DocUsername = DocUsername;
        this.doctor_id = doctor_id; }

    public void searchByUser(){
        if(txtfield.getText().equals("")){ warning.setText("You have to input data for search"); return; }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/patientrecord.fxml"));
        PatientManager pat = new PatientManager();
        DoctorManager dom = new DoctorManager();
        RecordManager rm = new RecordManager();
        Patients patient = pat.findByUsername(txtfield.getText());
        Doctors doctor = dom.getById(patient.getDoctor_id());
        Records rec = rm.getById(patient.getRecord_id());

        PatientRecordController patientRecordController = new PatientRecordController(patient, rec, doctor, "d");
        fxmlLoader.setController(patientRecordController);

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        stage.show();
        Stage s = (Stage) txtfield.getScene().getWindow();
        s.close();
    }

    public void logOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        stage.show();
        Stage s = (Stage) logout.getScene().getWindow();
        s.close();
    }

    public void addPatient() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addpatient.fxml"));
        AddPatientController apc = new AddPatientController(doctor_id);
        fxmlLoader.setController(apc);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        stage.show();
        Stage s = (Stage)txtfield.getScene().getWindow();
        //s.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientManager pdao = new PatientManager();
        DoctorManager ddao = new DoctorManager();
        try {
            patientList.setItems(pdao.allPatients(ddao.searchByUsername(DocUsername).getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        patientList.setOnMouseClicked(event -> {
            Patients selected = (Patients)patientList.getSelectionModel().getSelectedItem();
            Records rec = new Records();
            RecordManager rdi = new RecordManager();
            rec = rdi.getById(selected.getRecord_id());
            System.out.println(selected);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/patientrecord.fxml"));
            DoctorManager doctor = new DoctorManager();
            PatientRecordController pr = new PatientRecordController(selected, rec, doctor.searchByUsername(DocUsername), "d");
            fxmlLoader.setController(pr);
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
            stage.show();
            Stage s = (Stage)txtfield.getScene().getWindow();
            s.close();
        });
    }
}
