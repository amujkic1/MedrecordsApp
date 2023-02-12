package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

/**
 * Business logic layer for management of Appointments
 *
 * @author Ajna Mujkic
 */
public class AppointmentManager {

    /**
     * Method for getting all appointments from database
     * @return
     * @throws MyException
     */
    public List<Appointments> getAll() throws MyException {
        return DaoFactory.appointmentDao().getAll();
    }
    /**
     * Method for adding new Appointments to database
     * @param app
     * @throws MyException
     */
    public void add(Appointments app) throws MyException {
        DaoFactory.appointmentDao().add(app);
    }

    /**
     * Method for updating Appointment in database
     * @param app
     * @throws SQLException
     * @throws MyException
     */
    public void update(Appointments app) throws SQLException, MyException {
        DaoFactory.appointmentDao().update(app);
    }

    /**
     * Method that returns an Appointment given its id
     * @param id
     * @return
     * @throws MyException
     */
    public Appointments getById(int id) throws MyException {
        return DaoFactory.appointmentDao().getById(id);
    }

    /**
     * Method for deleting an Appointment from database
     * @param id
     * @throws MyException
     */
    public void delete(int id) throws MyException {
        DaoFactory.appointmentDao().delete(id);
    }

    /**
     * Method that returns an Appointment givent its patient_id
     * @param patient_id
     * @return
     * @throws SQLException
     */
    public Appointments searchByPatient(int patient_id) throws SQLException{
        return DaoFactory.appointmentDao().searchByPatient(patient_id);
    }

    /**
     * Method that returns all patients who have an appointment at doctor provided in parameter
     * @param doctor_id
     * @return
     * @throws SQLException
     */
    public ObservableList<String> haveAppointmentAtDoctor(int doctor_id) throws SQLException{
        return DaoFactory.appointmentDao().haveAppointmentAtDoctor(doctor_id);
    }

    /**
     *
     * Method that return number of appointments for the patient provided in first parameter
     * with the doctor provided in second parameter
     * @param patient_id
     * @param doctor_id
     * @return
     */
    public int countAppointments(int patient_id, int doctor_id){
        return DaoFactory.appointmentDao().countAppointments(patient_id, doctor_id);
    }

}
