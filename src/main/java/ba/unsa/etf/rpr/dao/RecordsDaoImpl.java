package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class RecordsDaoImpl extends AbstractDao<Records> implements RecordsDao {

    private static RecordsDaoImpl instance = null;
    private RecordsDaoImpl(){
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
        PatientManager patientManager = new PatientManager();
        Patients patient = patientManager.findByUsername(username);
        Records rec = null;
        try{
            Statement stmt = getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM RECORDS WHERE patient_id = " + patient.getId());
            while(res.next()){
                rec = new Records(res.getInt("patient_id"),
                        res.getInt("doctor_id"), res.getString("diagnosis"),
                        res.getString("allergies"), res.getString("prescriptions"),
                        res.getDouble("height"), res.getDouble("weight"), res.getString("blood"));
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
            rec.setPrescriptions(rs.getString("prescriptions"));
            rec.setHeight(rs.getDouble("height"));
            rec.setWeight(rs.getDouble("weight"));
            rec.setBlood(rs.getString("blood"));
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
        row.put("prescriptions", object.getPrescriptions());
        row.put("height", object.getHeight());
        row.put("weight", object.getWeight());
        row.put("blood", object.getBlood());
        return row;
    }


    public ObservableList<String> allergies(int patientID) throws SQLException {
        String query = "SELECT allergies FROM RECORDS WHERE ID = " + patientID;
        PreparedStatement stmt = getConnection().prepareStatement(query);

        ObservableList<String> result = FXCollections.observableArrayList();

        try {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result.add(rs.getString("allergies"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }

        return result;

    }

    public ObservableList<String> prescriptions(int patientID) throws SQLException {
        String query = "SELECT prescriptions FROM RECORDS WHERE ID = " + patientID;
        PreparedStatement stmt = getConnection().prepareStatement(query);

        ObservableList<String> result = FXCollections.observableArrayList();

        try {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result.add(rs.getString("prescriptions"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }

        return result;

    }

}