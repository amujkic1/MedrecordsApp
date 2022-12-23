package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DoctorsDaoImpl implements DoctorsDao {

    private Connection conn;
    public DoctorsDaoImpl(){
        Properties dbProp = new Properties();
        try{
            dbProp.load(DoctorsDaoImpl.class.getResource("/database.properties").openStream());
            conn = DriverManager.getConnection(dbProp.getProperty("url"), dbProp.getProperty("username"), dbProp.getProperty("password"));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    @Override
    public void add(Doctors item) {
        String query = "INSERT INTO DOCTORS(id, first_name, last_name, address, email," +
                "telephone, age, gender, specialization, password, username)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,item.getDoctor_id());
            stmt.setString(2,item.getFirst_name());
            stmt.setString(3,item.getLast_name());
            stmt.setString(4,item.getAddress());
            stmt.setString(5,item.getEmail());
            stmt.setString(6,item.getTelephone());
            stmt.setInt(7,item.getAge());
            stmt.setString(8,item.getGender());
            stmt.setString(9,item.getSpecialization());
            stmt.setString(10,item.getPassword());
            stmt.setString(11,item.getUsername());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    @Override
    public void update(Doctors item, int id) {
        String query = "UPDATE DOCTORS SET first_name = ?, last_name = ?, address = ?," +
                "email = ?, telephone = ?, age = ?, gender = ?, specialization = ?," +
                "password = ?, username = ?" +
                "WHERE doctor_id = " + id;
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,item.getDoctor_id());
            stmt.setString(2,item.getFirst_name());
            stmt.setString(3,item.getLast_name());
            stmt.setString(4,item.getAddress());
            stmt.setString(5,item.getEmail());
            stmt.setString(6,item.getTelephone());
            stmt.setInt(7,item.getAge());
            stmt.setString(8,item.getGender());
            stmt.setString(9,item.getSpecialization());
            stmt.setString(10,item.getPassword());
            stmt.setString(11,item.getUsername());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    @Override
    public Doctors getById(int id) {
        Doctors d = null;
        try{
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM DOCTORS WHERE id = " + id);
            while(res.next()){
                d = new Doctors(res.getInt("doctor_id"), res.getString("first_name"),
                        res.getString("last_name"), res.getString("address"),
                        res.getString("email"), res.getString("telephone"),
                        res.getInt("age"), res.getString("gender"),
                        res.getString("specialization"),
                        res.getString("password"), res.getString("username"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return d;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM DOCTORS WHERE id = " + id;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
    }

    public Doctors searchByUsername(String username){
        Doctors d = null;
        try{
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM DOCTORS WHERE username = '" + username + "'");
            while(res.next()){
                d = new Doctors(res.getInt("id"), res.getString("first_name"),
                        res.getString("last_name"), res.getString("address"),
                        res.getString("email"), res.getString("telephone"),
                        res.getInt("age"), res.getString("gender"),
                        res.getString("specialization"),
                        res.getString("password"), res.getString("username"));
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        return d;
    }
}
