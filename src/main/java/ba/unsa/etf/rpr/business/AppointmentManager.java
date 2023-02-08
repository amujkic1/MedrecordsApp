package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.sql.SQLException;
import java.util.List;

public class AppointmentManager {

    public List<Appointments> getAll() throws MyException {
        return DaoFactory.appointmentDao().getAll();
    }

    public void add(Appointments app) throws MyException {
        DaoFactory.appointmentDao().add(app);
    }

    public void update(Appointments app) throws SQLException, MyException {
        DaoFactory.appointmentDao().update(app);
    }

    public Appointments getById(int id) throws MyException {
        return DaoFactory.appointmentDao().getById(id);
    }

    public void delete(int id) throws MyException {
        DaoFactory.appointmentDao().delete(id);
    }
}