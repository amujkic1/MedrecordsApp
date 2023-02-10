package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AppointmentDaoImpl extends AbstractDao<Appointments> implements AppointmentDao{

    private static AppointmentDaoImpl instance = null;
    private AppointmentDaoImpl() {
        super("APPOINTMENTS");
    }

    public static AppointmentDaoImpl getInstance(){
        if(instance==null)
            instance = new AppointmentDaoImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Appointments rowToObject(ResultSet rs) throws MyException {
        try {
            Appointments app = new Appointments(
                    rs.getInt("patient_id"),
                    rs.getInt("doctor_id"),
                    LocalDate.parse("date"),
                    rs.getString("username"));
            return app;
        }catch (SQLException sqle){
            throw new MyException(sqle.getMessage(), sqle);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Appointments object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("patient_id", object.getPatient_id());
        row.put("doctor_id", object.getDoctor_id());
        row.put("date", object.getDate());
        row.put("username", object.getUsername());
        return row;
    }

    @Override
    public Appointments searchByPatient(int patient_id) throws SQLException {
        Appointments app = null;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM APPOINTMENTS WHERE patient_id = " + patient_id);
            while(res.next()){
                app = new Appointments(res.getInt("doctor_id"), res.getInt("patient_id"),
                        res.getDate("date").toLocalDate(), res.getString("username"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return app;
    }

    public ObservableList<String> haveAppointmentAtDoctor(int doctor_id) throws SQLException {
        String query = "SELECT username FROM APPOINTMENTS WHERE doctor_id = " + doctor_id;
        PreparedStatement stmt = getConnection().prepareStatement(query);

        ObservableList<String> result = FXCollections.observableArrayList();

        try {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result.add(rs.getString("username"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return result;
    }

    public int countAppointments(int patient_id, int doctor_id){
        int count = 0;

        String query = "SELECT COUNT(id) FROM APPOINTMENTS WHERE patient_id = " + patient_id + " AND doctor_id = " + doctor_id;
        try{
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            stmt.close();
        }catch (SQLException sqle) {
            System.out.println(sqle.getErrorCode());
        }
        return count;
    }

}