package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class RecordManager {

    public List<Records> getAll() throws MyException {
        return DaoFactory.recordsDao().getAll();
    }

    public void add(Records rec) throws MyException {
        DaoFactory.recordsDao().add(rec);
    }

    public void update(Records rec) throws SQLException, MyException {
        DaoFactory.recordsDao().update(rec);
    }

    public Records getById(int id) throws MyException {
        return DaoFactory.recordsDao().getById(id);
    }
    public void delete(int id) throws MyException {
        DaoFactory.recordsDao().delete(id);
    }

    public ObservableList<String> allergies(int patientID) throws SQLException{
        return DaoFactory.recordsDao().allergies(patientID);
    }

    public Records findUserRecord(String username){
        return DaoFactory.recordsDao().findUserRecord(username);
    }
    public ObservableList<String> prescriptions(int patientID) throws SQLException{
        return DaoFactory.recordsDao().prescriptions(patientID);
    }
}
