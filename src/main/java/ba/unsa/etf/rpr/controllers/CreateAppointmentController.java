package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.AppointmentManager;
import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAppointmentController implements Initializable {

    @FXML
    private Button okbttn;
    @FXML
    private ChoiceBox choice;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Label error;
    @FXML
    private Button cancel;
    int patient;

    public CreateAppointmentController(int patient_id){
        this.patient = patient_id;
    }

    public void createAppointment() throws MyException {

        Stage stage = (Stage) okbttn.getScene().getWindow();

        try {
            AppointmentManager am = new AppointmentManager();
            if(datepicker.getValue()==null || choice.getValue()==null) {
                error.setText("Please fill every field");
                System.out.println("You have left empty fields");
                return;
            }
            Doctors doc = (Doctors) choice.getValue();
            LocalDate ld = datepicker.getValue();
            Appointments app = new Appointments(patient, doc.getId(), ld);
            am.add(app);
        }
        catch (Exception e){
            throw new MyException(e.getMessage(), e);
        }

        stage.close();

    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DoctorManager dm = new DoctorManager();
        try {
            List<Doctors> doctors = dm.getAll();
            choice.getItems().addAll(doctors);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
    }
}
