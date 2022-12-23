package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.domain.Doctors;

public interface DoctorsDao extends Dao<Doctors> {
    Doctors searchByUsername(String username);
}
