package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Patients;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void add(T item);

    void update(T item, int id) throws SQLException;

    T getById(int id);

    void delete(int id);
}