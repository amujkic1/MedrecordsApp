package ba.unsa.etf.rpr.domain;

public class Doctors implements Idable {
    private int doctor_id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String telephone;
    private int age;
    private String gender;
    private String specialization;
    private String password;
    private String username;


    public Doctors(int doctor_id, String first_name, String last_name, String address, String email, String telephone,
                   int age, String gender, String specialization, String password, String username) {
        this.doctor_id = doctor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
        this.gender = gender;
        this.specialization = specialization;
        this.password = password;
        this.username = username;
    }

    public Doctors(){}

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    @Override
    public void setId(int id) {
        this.doctor_id = doctor_id;
    }

    @Override
    public int getId() {
        return doctor_id;
    }
}
