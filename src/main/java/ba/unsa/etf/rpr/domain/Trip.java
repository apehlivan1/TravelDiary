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
    private String review;

    public Trip(int userId, int destinationId, int rating, String review) {
        this.userId = userId;
        this.destinationId = destinationId;
        this.rating = rating;
        this.review = review;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "destinationId:" + destinationId +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}
