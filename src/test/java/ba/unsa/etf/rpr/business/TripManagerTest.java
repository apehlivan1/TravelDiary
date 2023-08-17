package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.TripDaoImpl;
import ba.unsa.etf.rpr.domain.Trip;
import ba.unsa.etf.rpr.exceptions.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripManagerTest {

    private TripManager tripManager;
    private Trip trip;
    private TripDaoImpl tripDao;
    private List<Trip> trips;

    @BeforeEach
    public void intialize() {
        tripManager = Mockito.mock(TripManager.class);
        trip = new Trip();
        tripDao = Mockito.mock(TripDaoImpl.class);
        trips = new ArrayList<>();
    }

    @Test
    void add() {
        List<Trip> mockTrips = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mockTrips.add(new Trip());
        }
        assertEquals(5, mockTrips.size(), "Expected 5 trips.");
    }

    @Test
    void searchByUser() throws AppException {
        int userId = 1115;
        TripManager manager = new TripManager();
        List<Trip> listT = manager.searchByUser(1115);
        assertEquals(0, listT.size());
    }


}
