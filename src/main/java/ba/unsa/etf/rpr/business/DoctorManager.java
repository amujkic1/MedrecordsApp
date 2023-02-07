package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.sql.SQLException;
import java.util.List;

public class DoctorManager {

    public String validateDoctorLogin(String username, String password){
        if(username==null) return "empty user field";
        if(password==null) return "empty password field";
        if(searchByUsername(username)==null) return "username does not exist";
        if(!searchByUsername(username).getPassword().equals(password)) return "wrong password";
        else return "ok";
    }
public List<Doctors> getAll() throws MyException {
    return DaoFactory.doctorDao().getAll();
}

public void add(Doctors doc) throws MyException {
    DaoFactory.doctorDao().add(doc);
}

public void update(Doctors doc) throws SQLException, MyException {
    DaoFactory.doctorDao().update(doc);
}

public Doctors getById(int id) throws MyException {
    return DaoFactory.doctorDao().getById(id);
}

public void delete(int id) throws MyException {
    DaoFactory.doctorDao().delete(id);
}

public Doctors searchByUsername(String username){
    return DaoFactory.doctorDao().searchByUsername(username);
}


}
