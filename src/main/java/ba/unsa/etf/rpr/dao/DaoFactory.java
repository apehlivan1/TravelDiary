package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation od DAOs
 *
 * @author Almedina Pehlivan
 */
public class DaoFactory {

    private static final CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    private static final DestinationDao destinationDao = DestinationDaoImpl.getInstance();
    private static final TripDao tripDao = TripDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();

    private DaoFactory() {}

    private static CategoryDao getCategoryDao() { return categoryDao; }
    private static DestinationDao getDestinationDao() { return destinationDao; }
    private static TripDao getTripDao() { return tripDao; }
    private static UserDao getUserDao() { return userDao; }
}
