package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

public interface TripDao extends Dao<Trip> {

    List<Trip> searchByUser(int userId) throws AppException;

    }
