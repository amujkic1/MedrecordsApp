package ba.unsa.etf.rpr.domain;

import java.sql.Date;

//builder pattern
public class Patients implements Idable {
    private int patient_id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String telephone;
    private int age;
    private String gender;
    private int record_id;
    private String password;
    private String username;
    private int doctor_id;

    public Patients() {}

    public Patients(String first_name, String last_name, String address, String email,
                    String telephone, int age, String gender, int record_id, String password, String username, int doctor_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
        this.gender = gender;
        this.record_id = record_id;
        this.password = password;
        this.username = username;
        this.doctor_id = doctor_id;
    }

    public Patients(int patient_id, String first_name, String last_name, String address, String email,
                    String telephone, int age, String gender, int record_id, String password, String username, int doctor_id) {
        this.patient_id = patient_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
        this.gender = gender;
        this.record_id = record_id;
        this.password = password;
        this.username = username;
        this.doctor_id = doctor_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString(){
        return first_name + " " + last_name;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    @Override
    public void setId(int id) {
        this.patient_id = patient_id;
    }

    @Override
    public int getId() {
        return patient_id;
    }
}
