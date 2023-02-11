package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface RecordsDao extends Dao<Records> {
    Records findUserRecord(String username) throws MyException;
    ObservableList<String> allergies(int patientID) throws SQLException;
    ObservableList<String> prescriptions(int patientID) throws SQLException;

}
