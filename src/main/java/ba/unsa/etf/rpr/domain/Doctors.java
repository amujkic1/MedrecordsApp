package ba.unsa.etf.rpr.domain;

public class Doctors implements Idable {
    //required parameters
    private int id;
    private final String password;
    private final String username;
    //optional parameters
    private final String first_name;
    private final String last_name;
    private final String specialization;

    //public Doctors(){}

    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }


    private Doctors(DoctorBuilder builder) {
        this.id = builder.id;
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.specialization = builder.specialization;
        this.password = builder.password;
        this.username = builder.username;
    }

    public static class DoctorBuilder{
        private int id;
        private final String password;
        private final String username;
        private String first_name = " ";
        private String last_name = " ";
        private String specialization = " ";

        public DoctorBuilder(int id, String username, String password){
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public DoctorBuilder First_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public DoctorBuilder Last_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public DoctorBuilder Specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public Doctors build(){
            return new Doctors(this);
        }
    }

    @Override
    public String toString(){
        return first_name + " " + last_name + "(" + specialization + ")";
    }

}
