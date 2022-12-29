package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;
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
                        res.getString("password"), res.getString("username"),
                        res.getInt("doctor_id"));
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
    public Patients findByUsername(String username) {
        Patients patient = null;
        try{
            Statement stmt = conn.createStatement();
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
            Statement stmt = conn.createStatement();
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
}
