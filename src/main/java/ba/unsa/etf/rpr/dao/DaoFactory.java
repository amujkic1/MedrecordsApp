package ba.unsa.etf.rpr.dao;

public class DaoFactory {

    private static final DoctorsDao doctorDao = DoctorsDaoImpl.getInstance();
    private static final PatientsDao patientDao = PatientsDaoImpl.getInstance();
    private static final RecordsDao recordDao = RecordsDaoImpl.getInstance();

    private static final AppointmentDao appointmentsDao = AppointmentDaoImpl.getInstance();
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

    public static AppointmentDao appointmentDao(){ return appointmentsDao; }

}