package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;

/**
 * List of all appointments made by patients
 *
 * @author Ajna Mujkic
 */
public class Appointments implements Idable{
    int id;
    int patient_id;
    int doctor_id;
    LocalDate date;

    String username;
    public Appointments(int patient_id, int doctor_id, LocalDate date, String username) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.username = username;
    }

    public int getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    public int getDoctor_id() {
        return doctor_id;
    }
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getId() {
        return id;
    }
}
