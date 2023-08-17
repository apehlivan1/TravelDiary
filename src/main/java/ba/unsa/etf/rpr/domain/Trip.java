package ba.unsa.etf.rpr.domain;

/**
 * A record of a past travel experience or destination visited by the user.
 * Class keeps track of users travel history and store their impressions.
 *
 * @author Almedina Pehlivan
 */
public class Trip implements Idable {

    private int id;
    private int userId;
    private int destinationId; //try with: private Destination destination;
    private int rating;
    private String note;

    public Trip() {}

    public Trip(int userId, int destinationId) {
        this.id = 0;
        this.userId = userId;
        this.destinationId = destinationId;
    }

    public Trip(int id, int userId, int destinationId, int rating, String note) {
        this.id = id;
        this.userId = userId;
        this.destinationId = destinationId;
        this.rating = rating;
        this.note = note;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int value) {
        id = value;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return note;
    }
}
