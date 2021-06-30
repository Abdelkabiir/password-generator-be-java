package PasswordManager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logins")
public class Login {

    @Id
    public String id;
    public String url;
    public String username;
    public String password;
    public Boolean deleted;

    public Login(String url, String username, String password, Boolean deleted) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
