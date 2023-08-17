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
        user = new User.Builder(113, "korisnik", "korisnik").build();
        userDaoMock = Mockito.mock(UserDaoImpl.class);
        users = new ArrayList<>();
        users.addAll(Arrays.asList(new User.Builder(1,"korisnik1","korisnik1").build(),
                new User.Builder(2, "korisnik2", "korisnik2").build(),
                new User.Builder(3,"korisnik3", "korisnik3").build()));
    }

    @Test
    void validateName() throws AppException {
        String correctUsername = "korisnik";
        try {
            Mockito.doCallRealMethod().when(userManager).validateName(correctUsername);
        } catch (AppException e) {
            //Test will fall if method validateName(name) throws an exception for correct parameter
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
        User newUser = new User.Builder(1, "new", "new").build();
        userManager.add(newUser);
        Assertions.assertTrue(true);
        Mockito.verify(userManager).add(newUser);
    }
}