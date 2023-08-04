package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

public interface DestinationDao extends Dao<Destination> {

    List<Destination> search(String text) throws AppException;
}
