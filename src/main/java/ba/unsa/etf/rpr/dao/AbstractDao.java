package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.*;
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
        return null;
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


}
