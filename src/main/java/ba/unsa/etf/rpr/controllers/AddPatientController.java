package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import ba.unsa.etf.rpr.domain.Patients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

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
    private TextField record_id_box;
    @FXML
    private TextField username_box;
    @FXML
    private TextField password_box;
    @FXML
    private Button addbttn;
    @FXML
    private Button cancelbttn;
    int doctor_id;
private Connection conn;

    public AddPatientController(int doctor_id){
        this.doctor_id = doctor_id;
    }
    public AddPatientController() {
        Properties dbProp = new Properties();
        try{
            dbProp.load(PatientsDaoImpl.class.getResource("/database.properties").openStream());
            conn = DriverManager.getConnection(dbProp.getProperty("url"), dbProp.getProperty("username"), dbProp.getProperty("password"));
        }catch (IOException e){
            e.printStackTrace();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    public void addPatientToBase(){
        PatientManager p = new PatientManager();
        DoctorManager doc = new DoctorManager();

        Patients patient = new Patients(first_name_box.getText(), last_name_box.getText(),
                address_box.getText(), email_box.getText(), telephone_box.getText(), Integer.parseInt(age_box.getText()),
                gender_box.getText(), Integer.parseInt(record_id_box.getText()), password_box.getText(),
                username_box.getText(), doctor_id);

        p.add(patient);

    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelbttn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
