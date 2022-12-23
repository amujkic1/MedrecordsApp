package ba.unsa.etf.rpr.domain;

import java.sql.Date;

public class Records {
    private int record_id;
    private int patient_id;
    private int doctor_id;
    private String diagnosis;
    private Date first_checkup_date;

    public Records(int record_id, int patient_id, int doctor_id, String diagnosis, Date first_checkup_date) {
        this.record_id = record_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
        this.first_checkup_date = first_checkup_date;
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

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getFirst_checkup_date() {
        return first_checkup_date;
    }

    public void setFirst_checkup_date(Date first_checkup_date) {
        this.first_checkup_date = first_checkup_date;
    }
}
