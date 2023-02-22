package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.AppException;
import java.util.List;

public interface Dao<T> {

    void add(T item) throws AppException;

    void update(T item) throws AppException;

    T getById(int id) throws AppException;

    void delete(int id) throws AppException;

    List<T> getAll() throws AppException;

}
