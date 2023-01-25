package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void add(T item);

    void update(T item) throws SQLException;

    T getById(int id);

    void delete(int id);

    public List<T> getAll();
}