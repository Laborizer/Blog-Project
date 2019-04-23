package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Class for the users of the application.
 *
 * <p>
 *     Automatically makes a database table out of this class using hibernate.
 * </p>
 */
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(generator = IDGenerator.GENERATOR_NAME)
    @GenericGenerator(
            name = IDGenerator.GENERATOR_NAME,
            strategy = "fi.tuni.blogproject.IDGenerator")
    private String id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    /**
     * Constructor for User.
     *
     * @param username Username of the User.
     * @param password Password of User.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Default constructor for User.
     */
    public User() {}

    /**
     * Gets the id of the User.
     *
     * @return Id of the User.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the username of the User.
     *
     * @return Username of the User.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for User.
     *
     * @param username Username to be assigned for User.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of User.
     *
     * @return User password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for User.
     *
     * @param password Password to be assigned for User.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
