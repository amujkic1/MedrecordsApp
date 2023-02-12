package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class PatientManager {

    public void validateUsername(String username) throws MyException {
        if(username.length() < 5 || username.length() > 20){
            throw new MyException("Username must be between 5 and 20 characters");
        }
    }

    public String validatePatientLogin(String username, String password) {

        if(username==null) return "empty user field";
        if(password==null) return "empty password field";
        if(findByUsername(username)==null) return "username does not exist";
        if(findByUsername(username).getRecord_id() == 0) return "no record";
        if(!findByUsername(username).getPassword().equals(password)) return "wrong password";
        else return "ok";
    }
    public List<Patients> getAll() throws MyException {
        return DaoFactory.patientsDao().getAll();
    }

    public void add(Patients pt) throws MyException {
        if (pt.getId() != 0){
            throw new MyException("Can't add patient with ID. ID is autogenerated");
        }
        validateUsername(pt.getUsername());

        try{
            DaoFactory.patientsDao().add(pt);
        }catch (MyException e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new MyException("Patient with same name exists");
            }
            throw e;
        }
    }

    public void update(Patients pt) throws SQLException, MyException {
        DaoFactory.patientsDao().update(pt);
    }

    public Patients getById(int id) throws MyException {

        try{
            return DaoFactory.patientsDao().getById(id);
        }catch (MyException e){
            throw new MyException("ID does not exist in the database");
        }

    }

    public void delete(int id) throws MyException {
        DaoFactory.patientsDao().delete(id);
    }

    public Patients findByUsername(String username){
        return DaoFactory.patientsDao().findByUsername(username);
    }

    public ObservableList<Patients> allPatients(int docId) throws SQLException{
        return DaoFactory.patientsDao().allPatients(docId);
    }
}
