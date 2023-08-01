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

    public static CategoryDao getCategoryDao() { return categoryDao; }
    public static DestinationDao getDestinationDao() { return destinationDao; }
    public static TripDao getTripDao() { return tripDao; }
    public static UserDao getUserDao() { return userDao; }
}
