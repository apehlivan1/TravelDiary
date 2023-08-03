package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

public class DestinationManager {

    public Destination add(Destination destination) throws AppException {
        return DaoFactory.getDestinationDao().add(destination);
    }

    public Destination update(Destination destination) throws AppException {
        return DaoFactory.getDestinationDao().update(destination);
    }

    public Destination getById(int id) throws AppException {
        return DaoFactory.getDestinationDao().getById(id);
    }

    public void delete(int id) throws AppException {
        DaoFactory.getDestinationDao().delete(id);
    }

    public List<Destination> getAll() throws AppException {
        return DaoFactory.getDestinationDao().getAll();
    }
}
