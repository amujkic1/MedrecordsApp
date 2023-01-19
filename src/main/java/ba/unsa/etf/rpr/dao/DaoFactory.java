package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;


public class DaoFactory {

    private static final DoctorsDao doctorDao = DoctorsDaoImpl.getInstance();
    private static final PatientsDao patientDao = PatientsDaoImpl.getInstance();
    private static final RecordsDao recordDao = RecordsDaoImpl.getInstance();

    private DaoFactory(){}

    public static DoctorsDao doctorDao(){
        return doctorDao;
    }

    public static PatientsDao patientsDao(){
        return patientDao;
    }

    public static RecordsDao recordsDao(){
        return recordDao;
    }

}
