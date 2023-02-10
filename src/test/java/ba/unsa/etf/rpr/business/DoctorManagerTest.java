package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DoctorsDaoImpl;
import ba.unsa.etf.rpr.dao.PatientsDaoImpl;
import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.List;

class DoctorManagerTest {

    private PatientManager patientManager;
    private Patients patient;
    private PatientsDaoImpl patientsDaoMock;
    private List<Patients> list;

    @BeforeEach
    public void initialzeObjectsWeNeed(){
        patientManager = Mockito.mock(PatientManager.class);
        patient = new Patients();

    }

}
