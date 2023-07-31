package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class DestinationDaoImpl extends AbstractDao<Destination> implements DestinationDao {

    private DestinationDaoImpl() {
        super("destinations");
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
        row.put("rating", object.getRating());
        return row;
    }
}
