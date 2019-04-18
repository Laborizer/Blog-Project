package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
