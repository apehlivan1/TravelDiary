package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.collections.ObservableList;

import java.util.List;

public class TripManager {

    public Trip add(Trip trip) throws AppException {
        return DaoFactory.getTripDao().add(trip);
    }

    public Trip update(Trip trip) throws AppException {
        return DaoFactory.getTripDao().update(trip);
    }

    public Trip getById(int id) throws AppException {
        return DaoFactory.getTripDao().getById(id);
    }

    public void delete(int id) throws AppException {
        DaoFactory.getTripDao().delete(id);
    }

    public List<Trip> getAll() throws AppException {
        return DaoFactory.getTripDao().getAll();
    }

    public List<Trip> searchByUser(int userId) throws AppException {
        return DaoFactory.getTripDao().searchByUser(userId);
    }

    public List<Double> getAllRatings(int destinationId) throws AppException {
        return DaoFactory.getTripDao().getAllRatings(destinationId);
    }

}
