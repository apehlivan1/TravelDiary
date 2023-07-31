package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class TripDaoImpl extends AbstractDao<Trip> implements TripDao {

    private TripDaoImpl() {
        super("trips");
    }

    @Override
    public Trip row2object(ResultSet rs) throws AppException {
        try {
            Trip trip = new Trip(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("destinationId"),
                    rs.getInt("rating"),
                    rs.getString("review"));
            return trip;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Trip object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("userId", object.getUserId());
        row.put("destinationId", object.getDestinationId());
        row.put("rating", object.getRating());
        row.put("review", object.getReview());
        return row;
    }
}
