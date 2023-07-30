package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("db.properties").openStream());
            String url = p.getProperty("url");
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            AbstractDao.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    public static Connection getConnection(){
        return AbstractDao.connection;
    }

    public abstract T row2object(ResultSet rs) throws AppException;

    public abstract Map<String, Object> object2row(T object);


    public T add(T item) throws AppException {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;

        } catch (SQLException e){
            throw new AppException(e.getMessage(), e);
        }
    }

    public T update(T item) throws AppException {
        return null;
    }

    public T getById(int id) throws AppException {
        return null;
    }

    public void delete(int id) throws AppException {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    public List<T> getAll() throws AppException {
        return null;
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }


}