package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;

public class UserManager {

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

    public void add(User user) throws AppException {
        DaoFactory.getUserDao().add(user);
    }
}
