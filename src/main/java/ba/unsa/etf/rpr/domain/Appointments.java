package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Appointments implements Idable{

    int id;
    int patient_id;
    int doctor_id;
    LocalDate date;

    public Appointments(int patient_id, int doctor_id, LocalDate date) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.date = date;
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

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
