package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDaoImpl;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.AppException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class UserManagerTest {

    private User user;
    private UserManager userManager;
    private UserDaoImpl userDaoMock;
    private List<User> users;

    @BeforeEach
    public void intialize() {
        userManager = Mockito.mock(UserManager.class);
        user = new User();
        user.setId(113);
        user.setUsername("korisnik");
        user.setPassword("korisnik");

        userDaoMock = Mockito.mock(UserDaoImpl.class);
        users = new ArrayList<>();
        users.addAll(Arrays.asList(new User("korisnik1"), new User("korisnik2"), new User("korisnik3")));
    }

    @Test
    void validateName() throws AppException {
        String correctUsername = "korisnik";
        try {
            Mockito.doCallRealMethod().when(userManager).validateName(correctUsername);
        } catch (AppException e) {
            //Test will fall if method validateCategoryName(name) throws an exception for correct parameter
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectShort = "A";
        Mockito.doCallRealMethod().when(userManager).validateName(incorrectShort);
        AppException exception1 = Assertions.assertThrows(AppException.class, () -> {
            userManager.validateName(incorrectShort);}, "Name must be between 3 and 45 chars");
        Assertions.assertEquals("Name must be between 3 and 45 chars", exception1.getMessage());

        String incorrectLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(userManager).validateName(incorrectLong);
        AppException exception2 = Assertions.assertThrows(AppException.class, () -> {
            userManager.validateName(incorrectLong);}, "Name must be between 3 and 45 chars");
        Assertions.assertEquals("Name must be between 3 and 45 chars", exception2.getMessage());
    }

    /**
     * Adding category that already exists
     */
    @Test
    void add() throws AppException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::getUserDao).thenReturn(userDaoMock);
        /*
        An exception will be thrown because our instance of User.java class has value for id
         */
        when(DaoFactory.getUserDao().getAll()).thenReturn(users);
        Mockito.doCallRealMethod().when(userManager).add(user);
        AppException exception = Assertions.assertThrows(AppException.class, () -> {
            userManager.add(user);}, "ID is autogenerated. Can't add user with ID.");

        Assertions.assertEquals("ID is autogenerated. Can't add user with ID.", exception.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::getUserDao);
        Mockito.verify(userManager).add(user);
        daoFactoryMockedStatic.close();
    }

    @Test
    void addNewUser() throws AppException {
        User newCategory = new User("Nova kategorija");
        userManager.add(newCategory);

        Assertions.assertTrue(true);
        Mockito.verify(userManager).add(newCategory);
    }
}
