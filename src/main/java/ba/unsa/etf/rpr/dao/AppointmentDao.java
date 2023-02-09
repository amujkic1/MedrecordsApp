package ba.unsa.etf.rpr.dao;
//dao class
import ba.unsa.etf.rpr.domain.Appointments;

import java.sql.SQLException;

public interface AppointmentDao extends Dao<Appointments>{

    Appointments searchByPatient(int patient_id) throws SQLException;

}
