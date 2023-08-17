package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static UserDaoImpl instance = null;

    private UserDaoImpl() {
        super("users");
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public User row2object(ResultSet rs) throws AppException {
        try {
            User user = new User.Builder(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password")).
                firstName(rs.getString("firstName")).
                lastName(rs.getString("lastName")).
                email(rs.getString("email")).
                phoneNumber(rs.getString("phoneNumber")).build();
            return user;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("username", object.getUsername());
        row.put("password", object.getPassword());
        row.put("firstName", object.getFirstName());
        row.put("lastName", object.getLastName());
        row.put("email", object.getEmail());
        row.put("phoneNumber", object.getPhoneNumber());
        return row;
    }

    @Override
    public User searchByUsername(String username) throws AppException {
        return executeQueryUnique("SELECT * FROM users WHERE username = ?", new Object[]{username});
    }
}
