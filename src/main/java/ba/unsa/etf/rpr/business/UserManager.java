package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;

public class UserManager {

    public User searchByUsername(String username) throws AppException {
        return DaoFactory.getUserDao().searchByUsername(username);
    }
}
