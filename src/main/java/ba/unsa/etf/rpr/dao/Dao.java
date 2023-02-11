package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctors;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void add(T item) throws MyException;

    void update(T item) throws MyException;

    T getById(int id) throws MyException;

    void delete(int id) throws MyException;

    List<T> getAll() throws MyException;
}