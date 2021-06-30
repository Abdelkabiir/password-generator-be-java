package PasswordManager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    public String id;
    public String username;
    public String dateOfBirth;
    public String password;
    public Boolean isLoggedIn = false;

    public User() {}

    public User(String username, String dateOfBirth, String password) {
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public User(String id, String username, String dateOfBirth, String password) {
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
