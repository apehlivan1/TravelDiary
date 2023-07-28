package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.AppException;
import java.util.List;

public interface Dao<T> {

    T add(T item) throws AppException;

    T update(T item) throws AppException;

    T getById(int id) throws AppException;

    void delete(int id) throws AppException;

    List<T> getAll() throws AppException;

}
