package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Patients;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PatientsDao extends Dao<Patients> {
    Patients findByUsername(String username);
    int numberOfRows();
    Patients searchByUsername(String username);

    public ObservableList<Patients> allPatients(int docId) throws SQLException;
}
