package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

public class UserManager {

    public User add(User user) throws AppException {
        return DaoFactory.getUserDao().add(user);
    }

    public User update(User user) throws AppException {
        return DaoFactory.getUserDao().update(user);
    }

    public User getById(int id) throws AppException {
        return DaoFactory.getUserDao().getById(id);
    }

    public void delete(int id) throws AppException {
        DaoFactory.getUserDao().delete(id);
    }

    public List<User> getAll() throws AppException {
        return DaoFactory.getUserDao().getAll();
    }

    public User searchByUsername(String username) throws AppException {
        return DaoFactory.getUserDao().searchByUsername(username);
    }

    public String validateLoginInfo(String username, String password) {
        try {
            if (searchByUsername(username) == null) return "Username is not valid!";
            if (!searchByUsername(username).getPassword().equals(password)) return "Password is not correct!";
            return "Login successful";
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }
}
