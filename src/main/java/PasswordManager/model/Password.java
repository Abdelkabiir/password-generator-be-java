package PasswordManager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "passwords")
public class Password {

    @Id
    public String id;
    public String password;
    public Boolean deleted;

    public Password(String password, Boolean deleted) {
        this.password = password;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
