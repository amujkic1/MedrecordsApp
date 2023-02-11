package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.exceptions.MyException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PatientManagerTest {

    private PatientManager patientManager;
    private Patients patient;
    private PatientsDaoImpl patientsDaoMock;
    private List<Patients> list;

    @BeforeEach
    public void initialzeObjectsWeNeed(){
        patientManager = Mockito.mock(PatientManager.class);
        patient = new Patients();
        patient.setUsername("EdnaGreen");
        patient.setId(1);

        patientsDaoMock = Mockito.mock(PatientsDaoImpl.class);
        list = new ArrayList<>();
        list.addAll(Arrays.asList(new Patients("EdnaGreen"), new Patients("HughPearce"),
                new Patients("AmyGeller"), new Patients("TomRiley"), new Patients("SethPark")));
    }

    @Test
    void validatePatientLogin() throws MyException {
        String correctName = "EdnaGreen";
        try{
            Mockito.doCallRealMethod().when(patientManager).validateUsername(correctName);
        }catch (MyException e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectUsernameShort = "E";
        Mockito.doCallRealMethod().when(patientManager).validateUsername(incorrectUsernameShort);
        MyException myException1 = Assertions.assertThrows(MyException.class, ()-> {
            patientManager.validateUsername(incorrectUsernameShort);}, "Username must be between 5 and 20 characters");
        Assertions.assertEquals("Username must be between 5 and 20 characters", myException1.getMessage());

        String incorrectUsernameLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(patientManager).validateUsername(incorrectUsernameLong);
        MyException myException2 = Assertions.assertThrows(MyException.class, () -> {
            patientManager.validateUsername(incorrectUsernameLong);}, "Username must be between 5 and 20 characters");
        Assertions.assertEquals("Username must be between 5 and 20 characters", myException2.getMessage());

    }

    /*@Test
    void add(){
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
    }*/




}





