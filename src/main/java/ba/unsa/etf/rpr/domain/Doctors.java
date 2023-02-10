package ba.unsa.etf.rpr.domain;

public class Doctors implements Idable {
    //required parameters
    private int id;
    private final String password;
    private final String username;
    //optional parameters
    private final String first_name;
    private final String last_name;
 //   private final String address;
   // private final String email;
   // private final String telephone;
   // private final int age;
   // private final String gender;
    private final String specialization;

    //public Doctors(){}

    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    //public String getAddress() {return address;}
    //public String getEmail() {return email;}
    //public String getTelephone() {return telephone;}
    //public int getAge() {return age;}
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
        //this.address = builder.address;
        //this.email = builder.email;
        //this.telephone = builder.telephone;
        //this.age = builder.age;
        //this.gender = builder.gender;
        this.specialization = builder.specialization;
        this.password = builder.password;
        this.username = builder.username;
    }

    //public String getGender() {return gender;}

    public static class DoctorBuilder{
        private int id;
        private final String password;
        private final String username;
        private String first_name = " ";
        private String last_name = " ";
        //private String address = " ";
        //private String email = " ";
        //private String telephone = " ";
        //private int age = 0;
        //private String gender = " ";
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

        /*public DoctorBuilder Address(String address) {
            this.address = address;
            return this;
        }

        public DoctorBuilder Email(String email) {
            this.email = email;
            return this;
        }

        public DoctorBuilder Telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public DoctorBuilder Age(int age) {
            this.age = age;
            return this;
        }

        public DoctorBuilder Gender(String gender) {
            this.gender = gender;
            return this;
        }*/

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
