package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.sql.SQLException;
import java.util.List;

/**
 * Business logic layer for management of Doctors
 *
 * @author Ajna Mujkic
 */
public class DoctorManager {
    /**
     * Method that checks if login data provided as parameters are correct
     * @param username
     * @param password
     * @return
     */
    public String validateDoctorLogin(String username, String password){
        if(username==null) return "empty user field";
        if(password==null) return "empty password field";
        if(searchByUsername(username)==null) return "username does not exist";
        if(!searchByUsername(username).getPassword().equals(password)) return "wrong password";
        else return "ok";
    }

    /**
     * Method for getting all doctors from database
     * @return
     * @throws MyException
     */
    public List<Doctors> getAll() throws MyException {
        return DaoFactory.doctorDao().getAll();
    }

    /**
     * Method for adding new doctor to database
     * @param doc
     * @throws MyException
     */
    public void add(Doctors doc) throws MyException {
        DaoFactory.doctorDao().add(doc);
    }

    /**
     * Method for updating a doctor in database
     * @param doc
     * @throws SQLException
     * @throws MyException
     */
    public void update(Doctors doc) throws SQLException, MyException {
        DaoFactory.doctorDao().update(doc);
    }

    /**
     * Method that returns a Doctor given its id
     * @param id
     * @return
     * @throws MyException
     */
    public Doctors getById(int id) throws MyException {
        try{
            return DaoFactory.doctorDao().getById(id);
        }catch (MyException e){
            throw new MyException("ID does not exist in the database");
        }
    }

    /**
     * Method for deleting a Doctor from database
     * @param id
     * @throws MyException
     */
    public void delete(int id) throws MyException {
        DaoFactory.doctorDao().delete(id);
    }

    /**
     * Method that returns a Doctor from database given its username
     * @param username
     * @return
     */
    public Doctors searchByUsername(String username){
    return DaoFactory.doctorDao().searchByUsername(username);
}

}
