package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
                    LocalDate.parse("date"));
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
        return row;
    }
}