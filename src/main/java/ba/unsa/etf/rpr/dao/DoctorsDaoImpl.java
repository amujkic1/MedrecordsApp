package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of the DAO
 *
 * @author Ajna Mujkic
 */
public class DoctorsDaoImpl extends AbstractDao<Doctors> implements DoctorsDao {
    private static DoctorsDaoImpl instance = null;
    private DoctorsDaoImpl(){
        super("DOCTORS");
    }

    @Override
    public Doctors rowToObject(ResultSet rs) throws MyException {
        try {
            Doctors doc = new Doctors.DoctorBuilder(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")).
                    First_name(rs.getString("first_name")).
                    Last_name(rs.getString("last_name")).
                    Specialization(rs.getString("specialization")).build();
            return doc;
        } catch (SQLException e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Doctors object){
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("first_name", object.getFirst_name());
        row.put("last_name", object.getLast_name());
        row.put("specialization", object.getSpecialization());
        row.put("password", object.getPassword());
        row.put("username", object.getUsername());
        return row;
    }

    public static DoctorsDaoImpl getInstance(){
        if(instance==null)
            instance = new DoctorsDaoImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    public Doctors searchByUsername(String username){
        Doctors d = null;
        try{
            Statement stmt = getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM DOCTORS WHERE username = '" + username + "'");
            while(res.next()){
                d = new Doctors.DoctorBuilder(
                        res.getInt("id"),
                        res.getString("username"),
                        res.getString("password")).
                        First_name(res.getString("first_name")).
                        Last_name(res.getString("last_name")).
                        Specialization(res.getString("specialization")).build();

            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return d;
    }

}