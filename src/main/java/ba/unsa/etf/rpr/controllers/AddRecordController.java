package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for adding new Record to database
 *
 * @author Ajna Mujkic
 */
public class AddRecordController {

    @FXML
    private TextField username;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField blood;
    @FXML
    private TextField allergies;
    @FXML
    private Button add;
    @FXML
    private Button cancel;
    @FXML
    private Label error;
    private int doctor_id;

    public AddRecordController(int doctor_id){
        this.doctor_id = doctor_id;
    }

    /**
     * Method for add Button
     * @throws SQLException
     * @throws MyException
     */
    public void addRecord() throws SQLException, MyException {

        RecordManager rm = new RecordManager();
        Stage stage = (Stage) add.getScene().getWindow();
        PatientManager pat = new PatientManager();
        Patients patient = pat.findByUsername(username.getText());
        if(patient == null){
            error.setText("Patient does not exist in the base");
            return;
        }

        try {
            Records record = new Records(patient.getId(), doctor_id, " ", allergies.getText(), " ", Double.parseDouble(height.getText()),
                    Double.parseDouble(height.getText()), blood.getText());
            rm.add(record);
            patient.setRecord_id(record.getId());
            patient.setDoctor_id(record.getDoctor_id());
            pat.update(patient);
        }catch (NumberFormatException ne){
            error.setText("Please enter numbers in height and weight field");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        stage.close();
    }

    /**
     * Method for cancel Button
     * @param event
     */
    public void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
