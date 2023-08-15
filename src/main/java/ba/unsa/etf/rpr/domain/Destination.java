package ba.unsa.etf.rpr.domain;

/**
 * Collection of travel destinations and travel ideas.
 *
 * @author Almedina Pehlivan
 */
public class Destination implements Idable {

    private int id;
    private String name;
    private String location;
    private String description;
    private int categoryId;
    private double averageRating;

    public Destination(int id, String name, String location, String description, int categoryId, double averageRating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.categoryId = categoryId;
        this.averageRating = averageRating;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int value) {
        this.id = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return name;
    }
}
