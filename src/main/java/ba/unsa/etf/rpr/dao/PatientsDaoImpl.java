package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Patients;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
public class PatientsDaoImpl implements PatientsDao{

    private Connection conn;
    public PatientsDaoImpl(){
        Properties dbProp = new Properties();
        try{
            dbProp.load(PatientsDaoImpl.class.getResource("/database.properties").openStream());
            conn = DriverManager.getConnection(dbProp.getProperty("url"), dbProp.getProperty("username"), dbProp.getProperty("password"));
        }catch (IOException e){
            e.printStackTrace();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    @Override
    public void add(Patients item) {
        String query = "INSERT INTO PATIENTS(id, first_name, last_name," +
                "address, email," +
                " telephone, age, gender," +
                "record_id, password, username ) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,item.getPatient_id());
            stmt.setString(2,item.getFirst_name());
            stmt.setString(3,item.getLast_name());
            stmt.setString(5,item.getAddress());
            stmt.setString(6, item.getEmail());
            stmt.setString(7, item.getTelephone());
            stmt.setInt(8,item.getAge());
            stmt.setString(9, item.getGender());
            stmt.setInt(10, item.getRecord_id());
            stmt.setString(11,item.getPassword());
            stmt.setString(12,item.getUsername());
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
                "password = ?, username = ?" +
                "WHERE patient_id = " + id;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,item.getPatient_id());
            stmt.setString(2,item.getFirst_name());
            stmt.setString(3,item.getLast_name());
            stmt.setString(5,item.getAddress());
            stmt.setString(6, item.getEmail());
            stmt.setString(7, item.getTelephone());
            stmt.setInt(8,item.getAge());
            stmt.setString(9, item.getGender());
            stmt.setInt(10, item.getRecord_id());
            stmt.setString(11,item.getPassword());
            stmt.setString(12,item.getUsername());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    @Override
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
                        res.getString("password"), res.getString("username"));
            }
            //vidi treba li stmt.executeUpdate
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return p;
    }

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
    }

    @Override
    public Patients findByName(String name) {
        return null;
    }
}
