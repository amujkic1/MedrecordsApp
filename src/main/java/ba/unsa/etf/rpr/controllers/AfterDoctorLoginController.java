package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.MainFX;
import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.dao.DoctorsDaoImpl;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import ba.unsa.etf.rpr.domain.Patients;
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

import static javafx.fxml.FXMLLoader.load;
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
//    @FXML
    public ListView patientList;
    //public PatientsDaoImpl somePatient;

    private Stage stage;
    private Scene scene;
    private Parent root;
    String DocUsername;
    public AfterDoctorLoginController(String DocUsername) { this.DocUsername = DocUsername; }

    public void searchByUser(){
        if(txtfield.getText().equals("")){ System.out.println("UNESITE PODATKE"); return; }
        PatientsDaoImpl pdao = new PatientsDaoImpl();
        Patients patient = pdao.findByUsername(txtfield.getText());
        System.out.println(patient.getUsername());
    }

    public void addPatient() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addpatient.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        stage.show();
        Stage s = (Stage)txtfield.getScene().getWindow();
        //s.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //AbstractDao pdao = new PatientsDaoImpl();
        //AbstractDao ddao = new DoctorsDaoImpl();
        PatientsDaoImpl pdao = new PatientsDaoImpl();
        DoctorsDaoImpl ddao = new DoctorsDaoImpl();
        try {
            patientList.setItems(pdao.allPatients(ddao.searchByUsername(DocUsername).getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
