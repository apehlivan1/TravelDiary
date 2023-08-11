package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.AppException;

public interface CategoryDao extends Dao<Category> {

    String getCategoryName(int id) throws AppException;
}
