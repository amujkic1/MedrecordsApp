package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName){
        try {
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(getClass().getResource("/database.properties").openStream());
            String url = p.getProperty("url");
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection(url, username, password);
        }catch (SQLException sqle){
            System.out.println(sqle.getErrorCode());
        }
        catch(IOException ioe){
            System.out.println("Greška u konekciji sa bazom");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public abstract T rowToObject(ResultSet rs) throws MyException;

    public abstract Map<String, Object> objectToRow(T object);

    public T getById(int id) throws MyException{
        return executeQueryUnique("SELECT * FROM " + tableName + " WHERE id = ?", new Object[]{id});
    }

    public List<T> getAll() throws MyException{
        return executeQuery("SELECT * FROM " + tableName, null);
    }

    public void delete(int id) throws MyException{
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    @Override
    public void add(T item) throws MyException{
        Map<String, Object> row = objectToRow(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter=1;
            for(Map.Entry<String, Object> entry : row.entrySet()){
                if(entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    public void update(T item) throws MyException {
        Map<String, Object> row = objectToRow(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(tableName).append(" SET ").append(updateColumns).append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for(Map.Entry<String, Object> entry : row.entrySet()){
                if(entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new MyException(e.getMessage(), e);
        }
    }

    public List<T> executeQuery(String query, Object[] params) throws MyException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null) {
                for (int i = 1; i <= params.length; i++) {
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while(rs.next()){
                resultList.add(rowToObject(rs));
            }
            return resultList;
        }catch (SQLException e){
            throw new MyException(e.getMessage(),e);
        }
    }

    public T executeQueryUnique(String query, Object[] params) throws MyException {
        List<T> result = executeQuery(query, params);
        if(result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new MyException("Object not found");
        }
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for(Map.Entry<String, Object> entry : row.entrySet()){
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if(row.size() != counter){
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter=0;
        for(Map.Entry<String, Object> entry : row.entrySet()){
            counter++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if(row.size()!=counter){
                columns.append(",");
            }
        }
        return columns.toString();
    }

}