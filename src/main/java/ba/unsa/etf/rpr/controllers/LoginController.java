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
    private Button registerbttn;
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
    @FXML
    private Label error;

    public LoginController(){}
    public void login(ActionEvent event) throws IOException, MyException {
        validateLogin();
    }

    public void validateLogin() throws IOException, MyException {

        DoctorManager doc = new DoctorManager();
        PatientManager pt = new PatientManager();

        if(choice.getValue()==null){ error.setText("This field is mandatory"); return; };

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

            if (code.equals("Username must be between 5 and 20 characters")){
                username.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                new animatefx.animation.Shake(username).play();
                error.setText("Username must be between 5 and 20 characters");
            }

            if(code.equals("ok")) {
                AfterDoctorLoginController afterDoctorLoginController = new AfterDoctorLoginController(username.getText(),
                        doc.searchByUsername(username.getText()).getId());
                newWindow("/fxml/afterdoctorlogin.fxml", afterDoctorLoginController, 1, 0);
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

            if(code.equals("no record")){
                error.setText("Please wait until your record is made");
                return;
            }

            if(code.equals("ok")) {
                PatientManager pat = new PatientManager();
                DoctorManager dom = new DoctorManager();
                RecordManager rm = new RecordManager();
                Patients patient = pat.findByUsername(username.getText());
                Doctors doctor = dom.getById(patient.getDoctor_id());
                Records rec = rm.getById(patient.getRecord_id());

                PatientRecordController patientRecordController = new PatientRecordController(patient, rec, doctor, "p");
                newWindow("/fxml/patientrecord.fxml", patientRecordController, 1, 1);

            }
        }
    }

    public void register() throws MyException, IOException {
        if(choice.getValue()==null){ error.setText("This field is mandatory"); return; };

        if(choice.getValue().equals("Doctor")) {
            DoctorManager dm = new DoctorManager();
            if(username.getText().equals("") || password.getText().equals("")){
                error.setText("Enter username and password");
                return;
            }
            else if(dm.searchByUsername(username.getText())==null) {
                Doctors doc = new Doctors.DoctorBuilder(dm.searchByUsername(username.getText()).getId(),
                        username.getText(), password.getText()).build();
                dm.add(doc);
            }else{
                error.setText("Username already exists");
                return;
            }
        }
        else if(choice.getValue().equals("Patient")){
            AddPatientController apc = new AddPatientController(0);
            newWindow("/fxml/addpatient.fxml", apc, 0, 1);
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        choice.getItems().addAll("Doctor", "Patient");
        user.setFont(new Font("Bahnschrift SemiBold", 14));
        pw.setFont(new Font("Bahnschrift SemiBold", 14));
        role.setFont(new Font("Bahnschrift SemiBold", 16));
    }

}