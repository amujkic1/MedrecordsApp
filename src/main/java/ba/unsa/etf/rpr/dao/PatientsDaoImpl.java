package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Patients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class PatientsDaoImpl extends AbstractDao<Patients> implements PatientsDao {

    //private Connection conn;
    private static PatientsDaoImpl instance = null;

    private PatientsDaoImpl(){
        super("PATIENTS");
    }

    public static PatientsDaoImpl getInstance(){
        if(instance==null)
            instance = new PatientsDaoImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    /*@Override
    public void add(Patients item) {
        String query = "INSERT INTO PATIENTS(id, first_name, last_name," +
                "address, email," +
                " telephone, age, gender," +
                "record_id, password, username, doctor_id ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,item.getPatient_id());
            stmt.setString(2,item.getFirst_name());
            stmt.setString(3,item.getLast_name());
            stmt.setString(4,item.getAddress());
            stmt.setString(5, item.getEmail());
            stmt.setString(6, item.getTelephone());
            stmt.setInt(7,item.getAge());
            stmt.setString(8, item.getGender());
            stmt.setInt(9, item.getRecord_id());
            stmt.setString(10,item.getPassword());
            stmt.setString(11,item.getUsername());
            stmt.setInt(12,item.getDoctor_id());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    @Override
    public void update(Patients item, int id) {
        String query = "UPDATE PATIENTS SET first_name = ?, last_name = ?, address = ?," +
                "email = ?, telephone = ?, age = ?, gender = ?, record_id = ?, " +
                "password = ?, username = ?, doctor_id = ?" +
                "WHERE id = " + id;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,item.getPatient_id());
            stmt.setString(2,item.getFirst_name());
            stmt.setString(3,item.getLast_name());
            stmt.setString(4,item.getAddress());
            stmt.setString(5, item.getEmail());
            stmt.setString(6, item.getTelephone());
            stmt.setInt(7,item.getAge());
            stmt.setString(8, item.getGender());
            stmt.setInt(9, item.getRecord_id());
            stmt.setString(10,item.getPassword());
            stmt.setString(11,item.getUsername());
            stmt.setInt(12,item.getDoctor_id());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }*/

    @Override
    public Patients rowToObject(ResultSet rs) {
        try {
            Patients pt = new Patients();
            pt.setId(rs.getInt("id"));
            pt.setFirst_name(rs.getString("first_name"));
            pt.setLast_name(rs.getString("last_name"));
            pt.setAddress(rs.getString("address"));
            pt.setEmail(rs.getString("email"));
            pt.setTelephone(rs.getString("telephone"));
            pt.setAge(rs.getInt("age"));
            pt.setGender(rs.getString("gender"));
            pt.setRecord_id(rs.getInt("record_id"));
            pt.setPassword(rs.getString("password"));
            pt.setUsername(rs.getString("username"));
            pt.setDoctor_id(rs.getInt("doctor_id"));
            return pt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Patients object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("first_name", object.getFirst_name());
        row.put("last_name", object.getLast_name());
        row.put("address", object.getAddress());
        row.put("email", object.getEmail());
        row.put("telephone", object.getTelephone());
        row.put("age", object.getAge());
        row.put("gender", object.getGender());
        row.put("record_id", object.getId());
        row.put("password", object.getPassword());
        row.put("username", object.getUsername());
        row.put("doctor_id", object.getDoctor_id());
        return row;
    }

   /* @Override
    public Patients getById(int id) {
        //sta ako nema kljuca pod id
        Patients p = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM PATIENTS WHERE id = " + id);
            while (res.next()) {
                p = new Patients(res.getInt("id"), res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("address"), res.getString("email"),
                        res.getString("telephone"), res.getInt("age"),
                        res.getString("gender"), res.getInt("record_id"),
                        res.getString("password"), res.getString("username"),
                        res.getInt("doctor_id"));
            }
            //vidi treba li stmt.executeUpdate
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return p;
    }*/

/*
    @Override
    public void delete(int id) {
        String query = "DELETE FROM PATIENTS WHERE id = " + id;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }*/

    @Override
    public Patients findByUsername(String username) {
        Patients patient = null;
        try{
            Statement stmt = getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM PATIENTS WHERE username = '" + username + "'");
            while(res.next()){
                patient = new Patients(res.getInt("id"), res.getString("first_name"),
                        res.getString("last_name"), res.getString("address"),
                        res.getString("email"), res.getString("telephone"),
                        res.getInt("age"), res.getString("gender"),
                        res.getInt("record_id"), res.getString("password"),
                        res.getString("username"), res.getInt("doctor_id"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return patient;
    }

    @Override
    public int numberOfRows() {
        int num=0;
        String query = "select count(id) from PATIENTS;";
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet res = stmt.executeQuery(query);
            /*while(res.next()){
                num = res.getInt(1);
            }*/
            res.next();
            num = res.getInt(1);
            System.out.println(num);
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return num;
    }

    @Override
    public Patients searchByUsername(String username) {
        Patients pt = null;
        try{
            Statement stmt = getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM PATIENTS WHERE username = '" + username + "'");
            while(res.next()){
                pt = new Patients(res.getInt("id"), res.getString("first_name"),
                        res.getString("last_name"), res.getString("address"),
                        res.getString("email"), res.getString("telephone"),
                        res.getInt("age"), res.getString("gender"),
                        res.getInt("record_id"), res.getString("password"),
                        res.getString("username"), res.getInt("doctor_id"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return pt;
    }

    public ObservableList<Patients> allPatients(int docId) throws SQLException {
        String query = "SELECT * FROM PATIENTS WHERE doctor_id = " + docId;
        PreparedStatement stmt = getConnection().prepareStatement(query);

        ObservableList<Patients> result = FXCollections.observableArrayList();

        try {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result.add(new Patients(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                        rs.getString(8), rs.getInt(9), rs.getString(10),
                        rs.getString(11), rs.getInt(12)));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }

        return result;

    }

}
