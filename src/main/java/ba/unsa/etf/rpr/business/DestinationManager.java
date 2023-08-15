package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;
import java.util.stream.Stream;

public class DestinationManager {

    public String validateUserInput(Destination destination) {
        boolean allFieldsHaveValues = Stream.of(
                destination.getName(),
                destination.getLocation(),
                destination.getDescription(),
                destination.getCategoryId(),
                destination.getAverageRating()
        ).noneMatch(value -> value == null || value.toString().trim().isEmpty());
        if (!allFieldsHaveValues || destination.getCategoryId() == 0 || destination.getAverageRating() == 0)
             return "Please enter all fields";

        if (destination.getName().length() > 45 || destination.getName().length() < 3)
            return "Name must be between 3 and 45 characters.";

        if (destination.getLocation().length() > 45 || destination.getLocation().length() < 3)
            return "Location must be between 3 and 45 characters.";
        return "";
    }

    public Destination add(Destination destination) throws AppException {
        return DaoFactory.getDestinationDao().add(destination);
    }

    public Destination update(Destination destination) throws AppException {
        return DaoFactory.getDestinationDao().update(destination);
    }

    public Destination getById(int id) throws AppException {
        return DaoFactory.getDestinationDao().getById(id);
    }

    public void delete(int id) throws AppException {
        DaoFactory.getDestinationDao().delete(id);
    }

    public List<Destination> getAll() throws AppException {
        return DaoFactory.getDestinationDao().getAll();
    }

    public List<Destination> search(String text) throws AppException {
        return DaoFactory.getDestinationDao().search(text);
    }

    public List<Destination> searchByCategory(int id) throws AppException {
        return DaoFactory.getDestinationDao().searchByCategory(id);
    }
}
