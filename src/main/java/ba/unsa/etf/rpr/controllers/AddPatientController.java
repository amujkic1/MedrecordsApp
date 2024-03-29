package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Controller for adding new Patient to database
 *
 * @author Ajna Mujkic
 */
public class AddPatientController implements Initializable {
    @FXML
    private TextField first_name_box;
    @FXML
    private TextField last_name_box;
    @FXML
    private TextField address_box;
    @FXML
    private TextField email_box;
    @FXML
    private TextField telephone_box;
    @FXML
    private TextField age_box;
    @FXML
    private TextField gender_box;
    @FXML
    private Button addbttn;
    @FXML
    private Button cancelbttn;
    @FXML
    private Label error;
    int doctor_id;
    String username;
    String password;

private Connection conn;

    public AddPatientController(int doctor_id, String username, String password){

        this.doctor_id = doctor_id;
        this.username = username;
        this.password = password;

    }

    /**
     * Method for add Button
     * @throws MyException
     */
    public void addPatientToBase() throws MyException {
        PatientManager p = new PatientManager();
        DoctorManager doc = new DoctorManager();

        Stage stage = (Stage) addbttn.getScene().getWindow();

        p.validateUsername(username);

        Patients patient = new Patients(first_name_box.getText(), last_name_box.getText(),
                address_box.getText(), email_box.getText(), telephone_box.getText(), Integer.parseInt(age_box.getText()),
                gender_box.getText(), 0, password, username, doctor_id);

        p.add(patient);

        stage.close();

    }

    /**
     * Method for cancel Button
     * @param event
     */
    public void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelbttn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
