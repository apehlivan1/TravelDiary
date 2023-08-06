package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

public class CategoryManager {

    public Category add(Category category) throws AppException {
        return DaoFactory.getCategoryDao().add(category);
    }

    public Category update(Category category) throws AppException {
        return DaoFactory.getCategoryDao().update(category);
    }

    public Category getById(int id) throws AppException {
        return DaoFactory.getCategoryDao().getById(id);
    }

    public void delete(int id) throws AppException {
        DaoFactory.getCategoryDao().delete(id);
    }

    public List<Category> getAll() throws AppException {
        return DaoFactory.getCategoryDao().getAll();
    }

}
