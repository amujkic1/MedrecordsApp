package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.domain.Records;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface RecordsDao extends Dao<Records> {

    public ObservableList<String> allergies(int patientID) throws SQLException;
    public ObservableList<String> prescriptions(int patientID) throws SQLException;

}
