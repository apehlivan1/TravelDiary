package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TripDaoImpl extends AbstractDao<Trip> implements TripDao {

    private static TripDaoImpl instance = null;

    private TripDaoImpl() {
        super("trips");
    }

    public static TripDaoImpl getInstance() {
        if (instance == null)
            instance = new TripDaoImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public Trip row2object(ResultSet rs) throws AppException {
        try {
            Trip trip = new Trip(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("destinationId"),
                    rs.getInt("rating"),
                    rs.getString("note"));
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
        row.put("note", object.getNote());
        return row;
    }

    @Override
    public List<Trip> searchByUser(int userId) throws AppException {
        return executeQuery("SELECT * FROM trips WHERE userId = ?", new Object[]{userId});
    }
}
