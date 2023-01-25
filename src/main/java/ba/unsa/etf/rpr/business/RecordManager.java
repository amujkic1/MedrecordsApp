package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Records;

import java.sql.SQLException;
import java.util.List;

public class RecordManager {

    public List<Records> getAll(){
        return DaoFactory.recordsDao().getAll();
    }

    public void add(Records rec){
        DaoFactory.recordsDao().add(rec);
    }

    public void update(Records rec) throws SQLException {
        DaoFactory.recordsDao().update(rec);
    }

    public Records getById(int id){
        return DaoFactory.recordsDao().getById(id);
    }

    public void delete(int id){
        DaoFactory.recordsDao().delete(id);
    }

}
