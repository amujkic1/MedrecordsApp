package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for deleting records
 *
 * @author Ajna Mujkic
 */
public class DeleteRecordController {

    @FXML
    private TextField username;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;

    public DeleteRecordController(){}

    /**
     * Method for delete Button
     * @throws MyException
     */
    public void deleteUser() throws MyException {

        Stage stage = (Stage) cancel.getScene().getWindow();

        RecordManager recordManager = new RecordManager();
        Records record = recordManager.findUserRecord(username.getText());

        recordManager.delete(record.getId());

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
