package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DestinationDaoImpl extends AbstractDao<Destination> implements DestinationDao {

    private static DestinationDaoImpl instance = null;

    private DestinationDaoImpl() {
        super("destinations");
    }

    public static DestinationDaoImpl getInstance() {
        if (instance == null)
            instance = new DestinationDaoImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public Destination row2object(ResultSet rs) throws AppException {
        try {
            Destination destination = new Destination(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("description"),
                    rs.getInt("categoryId"), //try using DaoFactory.categoryDao() method to get by id
                    rs.getInt("rating")
            );
            return destination;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Destination object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("location", object.getLocation());
        row.put("description", object.getDescription());
        row.put("categoryId", object.getCategoryId());
        row.put("rating", object.getAverageRating());
        return row;
    }

    @Override
    public List<Destination> search(String text) throws AppException {
        return executeQuery("SELECT * FROM destinations WHERE name LIKE concat('%', ?, '%')", new Object[]{text});
    }

    @Override
    public List<Destination> searchByCategory(int id) throws AppException {
        return executeQuery("SELECT * FROM destinations WHERE categoryId = ", new Object[]{id});
    }

}
