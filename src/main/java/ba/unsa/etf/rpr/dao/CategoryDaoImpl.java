package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    private CategoryDaoImpl() {
        super("categories");
    }

    @Override
    public Category row2object(ResultSet rs) throws AppException {
        try {
            Category cat = new Category();
            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("name"));
            return cat;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Category object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        return row;
    }
}
