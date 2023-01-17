package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Records;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class RecordsDaoImpl extends AbstractDao<Records> implements RecordsDao {

    private static RecordsDaoImpl instance = null;
    public RecordsDaoImpl(){
        super("RECORDS");
    }

    public static RecordsDaoImpl getInstance(){
        if(instance==null)
            instance = new RecordsDaoImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    public Records findUserRecord(String username){
        Records rec = null;
        try{
            Statement stmt = getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM RECORDS WHERE username = '" + username + "'");
            while(res.next()){
                rec = new Records(res.getInt("id"), res.getInt("patient_id"),
                        res.getInt("doctor_id"), res.getString("diagnosis"),
                        res.getString("allergies"), res.getString("treatments"),
                        res.getDouble("height"), res.getDouble("weight"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return rec;
    }



    @Override
    public Records rowToObject(ResultSet rs) {
        try {
            Records rec = new Records();
            rec.setId(rs.getInt("id"));
            rec.setPatient_id(rs.getInt("patient_id"));
            rec.setDoctor_id(rs.getInt("doctor_id"));
            rec.setDiagnosis(rs.getString("diagnosis"));
            rec.setAllergies(rs.getString("allergies"));
            rec.setTreatments(rs.getString("treatments"));
            rec.setHeight(rs.getDouble("height"));
            rec.setWeight(rs.getDouble("weight"));
            return rec;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Map<String, Object> objectToRow(Records object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("patient_id", object.getPatient_id());
        row.put("doctor_id", object.getDoctor_id());
        row.put("diagnosis", object.getDiagnosis());
        row.put("allergies", object.getAllergies());
        row.put("treatments", object.getTreatments());
        row.put("height", object.getHeight());
        row.put("weight", object.getWeight());
        return row;
    }


}
