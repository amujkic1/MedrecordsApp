package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
/**
 * Business logic layer for management of Records
 *
 * @author Ajna Mujkic
 */
public class RecordManager {

    /**
     * Method for getting all Records from database
     * @return
     * @throws MyException
     */
    public List<Records> getAll() throws MyException {
        return DaoFactory.recordsDao().getAll();
    }

    /**
     * Method for adding new Record to database
     * @param rec
     * @throws MyException
     */
    public void add(Records rec) throws MyException {
        if (rec.getId() != 0){
            throw new MyException("Can't add record with ID. ID is autogenerated");
        }

        try{
            DaoFactory.recordsDao().add(rec);
        }catch (MyException e){
            throw new MyException(e.getMessage());
        }
    }

    /**
     * Method for updating a Record in database
     * @param rec
     * @throws SQLException
     * @throws MyException
     */
    public void update(Records rec) throws SQLException, MyException {
        DaoFactory.recordsDao().update(rec);
    }

    /**
     * Method that returns a Record given its id
     * @param id
     * @return
     * @throws MyException
     */
    public Records getById(int id) throws MyException {
        return DaoFactory.recordsDao().getById(id);
    }

    /**
     * Method for deleting a Record from database
     * @param id
     * @throws MyException
     */
    public void delete(int id) throws MyException {
        DaoFactory.recordsDao().delete(id);
    }

    /**
     * Method that returns allergies for a patient with id provided as parameter
     * @param patientID
     * @return
     * @throws SQLException
     */
    public ObservableList<String> allergies(int patientID) throws SQLException{
        return DaoFactory.recordsDao().allergies(patientID);
    }

    /**
     * Method that returns a Record for the patient provided as function parameter
     * @param username
     * @return
     * @throws MyException
     */
    public Records findUserRecord(String username) throws MyException {
        try {
            return DaoFactory.recordsDao().findUserRecord(username);
        } catch (NullPointerException e){
            throw new NullPointerException("Patient does not exist in the database");
        }
    }

    /**
     * Method that returns prescriptions for a patient with id provided as parameter
     * @param patientID
     * @return
     * @throws SQLException
     */
    public ObservableList<String> prescriptions(int patientID) throws SQLException{
        return DaoFactory.recordsDao().prescriptions(patientID);
    }

}
