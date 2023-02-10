package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Appointments;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface AppointmentDao extends Dao<Appointments>{

    Appointments searchByPatient(int patient_id) throws SQLException;
    public ObservableList<String> haveAppointmentAtDoctor(int doctor_id) throws SQLException;

}
