package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Business Logic Layer for management of Trips
 *
 * @author Almedina Pehlivan
 */
public class TripManager {

    /**
     * Adding new trip to database
     */
    public Trip add(Trip trip) throws AppException {
        return DaoFactory.getTripDao().add(trip);
    }

    /**
     * Updating a trip in database
     */
    public Trip update(Trip trip) throws AppException {
        return DaoFactory.getTripDao().update(trip);
    }

    /**
     * Looking for a trip with the provided id
     */
    public Trip getById(int id) throws AppException {
        return DaoFactory.getTripDao().getById(id);
    }

    /**
     * Deleting a trip with the provided id
     */
    public void delete(int id) throws AppException {
        DaoFactory.getTripDao().delete(id);
    }

    /**
     * Getting all trips from database
     */
    public List<Trip> getAll() throws AppException {
        return DaoFactory.getTripDao().getAll();
    }

    /**
     * Searching for trips linked to a user
     */
    public List<Trip> searchByUser(int userId) throws AppException {
        return DaoFactory.getTripDao().searchByUser(userId);
    }

    /**
     * @return All ratings associated with a destination
     */
    public List<Double> getAllRatings(int destinationId) throws AppException {
        return DaoFactory.getTripDao().getAllRatings(destinationId);
    }

}
