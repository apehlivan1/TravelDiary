package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;

public interface UserDao extends Dao<User> {

    User searchByUsername(String username) throws AppException;

}
