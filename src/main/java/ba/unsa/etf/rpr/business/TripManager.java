package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;

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

}
