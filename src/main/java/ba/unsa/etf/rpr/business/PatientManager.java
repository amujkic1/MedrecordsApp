package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Patients;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class PatientManager {

    public String validatePatientLogin(String username, String password){
        if(username==null) return "empty user field";
        if(password==null) return "empty password field";
        if(findByUsername(username)==null) return "username does not exist";
        if(!findByUsername(username).getPassword().equals(password)) return "wrong password";
        else return "ok";
    }
    public List<Patients> getAll(){
        return DaoFactory.patientsDao().getAll();
    }

    public void add(Patients pt){
        DaoFactory.patientsDao().add(pt);
    }

    public void update(Patients pt) throws SQLException {
        DaoFactory.patientsDao().update(pt);
    }

    public Patients getById(int id){
        return DaoFactory.patientsDao().getById(id);
    }

    public void delete(int id){
        DaoFactory.patientsDao().delete(id);
    }

    public Patients findByUsername(String username){
        return DaoFactory.patientsDao().findByUsername(username);
    }

    public ObservableList<Patients> allPatients(int docId) throws SQLException{
        return DaoFactory.patientsDao().allPatients(docId);
    }
}
