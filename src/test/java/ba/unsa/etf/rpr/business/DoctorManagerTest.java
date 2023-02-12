package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.exceptions.MyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorManagerTest {

    @Test
    public void searchByUsername1(){

        String username = "JohnDoe";
        DoctorManager doctorManager = new DoctorManager();
        Doctors doctor = doctorManager.searchByUsername(username);

        assertEquals(null, doctor);

    }

    @Test
    public void findByUsername2(){

        String username = "SarahTaylor";
        DoctorManager doctorManager = new DoctorManager();
        Doctors doctor = doctorManager.searchByUsername(username);

        assertNotEquals(null, doctor);
    }

    @Test
    public  void getById() throws MyException {

        int id = -1;
        DoctorManager doctorManager = new DoctorManager();

        MyException myException = Assertions.assertThrows(MyException.class, ()->{
            doctorManager.getById(id);}, "ID does not exist in the database");
        Assertions.assertEquals("ID does not exist in the database", myException.getMessage());

    }




}






