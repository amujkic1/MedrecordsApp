package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;

/**
 * Dao interface for Doctor domain bean
 *
 * @author Ajna Mujkic
 */
public interface DoctorsDao extends Dao<Doctors> {
    Doctors searchByUsername(String username);

}
