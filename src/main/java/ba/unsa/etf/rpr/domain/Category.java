package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Represents the different types of trips or tourist destinations
 *
 * @author Almedina Pehlivan
 */
public class Category implements Idable {

    private int id;
    private String name;

    public Category() {}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int value) {
        id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
