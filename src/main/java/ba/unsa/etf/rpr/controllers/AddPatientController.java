package ba.unsa.etf.rpr.controllers;

//napravi metodu generatId koja daje sljedeci moguci id u tabeli na nacin da vrati preko fje COUNT broj redova i da sljedeci
// provjera da li postoji zadani id za svaki slucaj

import ba.unsa.etf.rpr.dao.DoctorsDaoImpl;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import ba.unsa.etf.rpr.domain.Patients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class AddPatientController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Label first_name;
    @FXML
    private TextField first_name_box;
    @FXML
    private Label last_name;
    @FXML
    private TextField last_name_box;
    @FXML
    private TextField address_box;
    @FXML
    private Label address;
    @FXML
    private TextField email_box;
    @FXML
    private Label email;
    @FXML
    private TextField telephone_box;
    @FXML
    private Label telephone;
    @FXML
    private TextField age_box;
    @FXML
    private Label age;
    @FXML
    private TextField gender_box;
    @FXML
    private Label gender;
    @FXML
    private TextField record_id_box;
    @FXML
    private Label record_id;
    @FXML
    private TextField username_box;
    @FXML
    private Label username;
    @FXML
    private TextField password_box;
    @FXML
    private Label password;
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
        PatientsDaoImpl p = new PatientsDaoImpl();
        DoctorsDaoImpl doc = new DoctorsDaoImpl();

        System.out.println(doctor_id);

        Patients patient = new Patients(first_name_box.getText(), last_name_box.getText(),
                address_box.getText(), email_box.getText(), telephone_box.getText(), Integer.parseInt(age_box.getText()),
                gender_box.getText(), Integer.parseInt(record_id_box.getText()), password_box.getText(),
                username_box.getText(), doctor_id);

        p.add(patient);

    }

    public int generateId(){
        PatientsDaoImpl ptdao = new PatientsDaoImpl();
        int num = ptdao.numberOfRows();
        return num+1;
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelbttn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
