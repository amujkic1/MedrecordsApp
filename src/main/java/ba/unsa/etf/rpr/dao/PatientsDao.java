package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Patients;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * Dao interface for Appointment domain bean
 *
 * @author Ajna Mujkic
 */
public interface PatientsDao extends Dao<Patients> {
    Patients findByUsername(String username);
    ObservableList<Patients> allPatients(int docId) throws SQLException;
}
