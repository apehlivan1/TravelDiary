package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Destination;

public class AddTripController {

    private int userId;
    private Destination destination;

    public AddTripController(int userId, Destination destination) {
        this.userId = userId;
        this.destination = destination;
    }
}
