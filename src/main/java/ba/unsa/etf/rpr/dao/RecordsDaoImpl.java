package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Records;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class RecordsDaoImpl extends AbstractDao<Records> implements RecordsDao {

    //private Connection conn;
    private static RecordsDaoImpl instance = null;
    public RecordsDaoImpl(){
        super("RECORDS");
        /*Properties dbProp = new Properties();
        try{
            dbProp.load(RecordsDaoImpl.class.getResource("/database.properties").openStream());
            conn = DriverManager.getConnection(dbProp.getProperty("url"), dbProp.getProperty("username"), dbProp.getProperty("password"));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch(SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }*/
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

    /*@Override
    public void add(Records item) {
        String query = "INSERT INTO RECORDS(id, patient_id, doctor_id, diagnosis, first_checkup_date)" +
                "VALUES(?,?,?,?,?)";
        try{
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,item.getRecord_id());
        stmt.setInt(2,item.getPatient_id());
        stmt.setInt(3,item.getDoctor_id());
        stmt.setString(4,item.getDiagnosis());
        stmt.setDate(5,item.getFirst_checkup_date());
        stmt.executeUpdate();
        stmt.close();
        }catch(SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }

    }

    @Override
    public void update(Records item, int id) {
        String query = "UPDATE RECORDS SET id = ?, patient_id = ?, doctor_id = ?" +
                "diagnosis = ?, first_checkup_date = ?" +
                "WHERE record_id = " + id;
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,item.getRecord_id());
            stmt.setInt(2,item.getPatient_id());
            stmt.setInt(3,item.getDoctor_id());
            stmt.setString(4,item.getDiagnosis());
            stmt.setDate(5,item.getFirst_checkup_date());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }*/

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

    /*@Override
    public Records getById(int id) {
        Records r = null;
        try{
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM RECORDS WHERE id = " + id);
            while(res.next()){
                r = new Records(res.getInt("id"), res.getInt("patient_id"),
                        res.getInt("doctor_id"), res.getString("diagnosis"),
                        res.getDate("first_checkup_date")) {
                };
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return r;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM RECORDS WHERE id = " + id;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }*/
}
