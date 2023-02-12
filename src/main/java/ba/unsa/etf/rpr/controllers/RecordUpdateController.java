package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecordUpdateController implements Initializable {
    @FXML
    private TextArea txtarea;
    @FXML
    private Button add;
    @FXML
    private Button cancel;
    @FXML
    private Label label;
    private int record_id;
    private String who;

    String clicked;
    RecordUpdateController(int record_id, String clicked, String who){
        this.record_id = record_id;
        this.clicked = clicked;
        this.who = who;
    }

    public void add() throws SQLException, MyException {

        Stage stage = (Stage) add.getScene().getWindow();

        if(clicked.equals("diagnosis")) {
            RecordManager rm = new RecordManager();
            Records newRecord = rm.getById(record_id);
            newRecord.setDiagnosis(txtarea.getText());
            rm.update(newRecord);
        }else{
            RecordManager rm = new RecordManager();
            Records newRecord = rm.getById(record_id);
            newRecord.setPrescriptions(txtarea.getText());
            rm.update(newRecord);
        }

        stage.close();
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        label.setText("Write new " + clicked +  " here");
    }

}