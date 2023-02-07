package ba.unsa.etf.rpr.domain;

public class Records implements Idable {
    private int id;
    private int patient_id;
    private int doctor_id;
    private String diagnosis;
    private String allergies;
    private String prescriptions;
    private Double height;
    private Double weight;
    private String blood;


    public Records(){}
    public Records(int patient_id, int doctor_id, String diagnosis, String allergies, String prescriptions,
                   Double height, Double weight, String blood) {
        //this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
        this.allergies = allergies;
        this.prescriptions = prescriptions;
        this.height = height;
        this.weight = weight;
        this.blood = blood;
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

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String treatments) {
        this.prescriptions = treatments;
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

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
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
