package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.domain.Patients;

public interface PatientsDao extends Dao<Patients> {
    Patients findByUsername(String username);
    int numberOfRows();
}
