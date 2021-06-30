package PasswordManager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

    @Id
    public String id;
    public String title;
    public String body;
    public String date;
    public Boolean deleted;

    public Note(String title, String body, String date, Boolean deleted) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
