package ba.unsa.etf.rpr.domain;

public class Destination implements Idable {
    int id;
    String name;
    String location;
    String description;
    int rating;

    public Destination(int id, String name, String location, String description, int rating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
