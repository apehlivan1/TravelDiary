package ba.unsa.etf.rpr.domain;

/**
 * List of users from database
 *
 * @author Almedina Pehlivan
 */
public class User implements Idable {

    private int id;
    private final String username;
    private final String password;

    //optional parameters
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;


    public User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int value) {
        id = value;
    }

    public static class Builder {

        private int id;
        private String username;
        private String password;
        private String firstName = "";
        private String lastName = "";
        private String email = "";
        private String phoneNumber = "";

        public Builder (int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
