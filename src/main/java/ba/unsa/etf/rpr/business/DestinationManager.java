package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;
import java.util.stream.Stream;

/**
 * Business Logic Layer for management of Destinations
 *
 * @author Almedina Pehlivan
 */
public class DestinationManager {

    /**
     * Validates the user input for a given destination.
     *
     * It ensures that all required fields are non-null and non-empty, and that the
     * specified length constraints for name and location are met.
     * @param destination The destination object to be validated.
     * @return An error message indicating the validation failure, or an empty string if validation passes.
     */
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

    /**
     * Adding new destination to database
     */
    public Destination add(Destination destination) throws AppException {
        return DaoFactory.getDestinationDao().add(destination);
    }

    /**
     * Updating a destination in database
     */
    public Destination update(Destination destination) throws AppException {
        return DaoFactory.getDestinationDao().update(destination);
    }

    /**
     * Looking for a destination with provided id
     */
    public Destination getById(int id) throws AppException {
        return DaoFactory.getDestinationDao().getById(id);
    }

    /**
     * Deleting a destination with provided id
     */
    public void delete(int id) throws AppException {
        DaoFactory.getDestinationDao().delete(id);
    }

    /**
     * Getting all destinations from database
     */
    public List<Destination> getAll() throws AppException {
        return DaoFactory.getDestinationDao().getAll();
    }

    /**
     * Searching for a destination with text in its name
     */
    public List<Destination> search(String text) throws AppException {
        return DaoFactory.getDestinationDao().search(text);
    }

    /**
     * @param id category id
     * @return all destinations that belong to specific category
     * @throws AppException
     */
    public List<Destination> searchByCategory(int id) throws AppException {
        return DaoFactory.getDestinationDao().searchByCategory(id);
    }
}
