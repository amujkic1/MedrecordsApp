package ba.unsa.etf.rpr.domain;

import java.sql.Date;

public class Records implements Idable {
    private int record_id;
    private int patient_id;
    private int doctor_id;
    private String diagnosis;
    private String allergies;
    private String treatments;
    private Double height;
    private Double weight;

    public Records(){}
    public Records(int record_id, int patient_id, int doctor_id, String diagnosis, String allergies, String treatments,
                   Double height, Double weight) {
        this.record_id = record_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
        this.allergies = allergies;
        this.treatments = treatments;
        this.height = height;
        this.weight = weight;
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

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public void setId(int id) {
        this.record_id = record_id;
    }

    @Override
    public int getId() {
        return record_id;
    }
}
