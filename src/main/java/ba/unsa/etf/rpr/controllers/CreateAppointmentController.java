package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.AppointmentManager;
import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAppointmentController implements Initializable {

    @FXML
    private Button okbttn;
    @FXML
    private ChoiceBox choice;
    @FXML
    private DatePicker datepicker;
    int patient;

    public CreateAppointmentController(int patient_id){
        this.patient = patient_id;
    }


    public void createAppointment() throws MyException {

        Stage stage = (Stage) okbttn.getScene().getWindow();

        AppointmentManager am = new AppointmentManager();
        Doctors doc = (Doctors)choice.getValue();
        LocalDate ld = datepicker.getValue();
        Appointments app = new Appointments(patient, doc.getId(), ld);
        am.add(app);

        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DoctorManager dm = new DoctorManager();
        List<Doctors> doctors = new ArrayList<>();
        try {
            doctors = dm.getAll();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
        choice.getItems().addAll(doctors);
    }
}
