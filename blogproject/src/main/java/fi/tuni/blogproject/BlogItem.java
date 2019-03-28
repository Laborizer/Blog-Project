package fi.tuni.blogproject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BlogItem {
    long id;
    Date creationDate;
    String author;
    String title;
    String content;

    public BlogItem(long id, Date creationDate, String author, String title, String content) {
        this.creationDate = creationDate;
        this.author = author;
        this.title = title;
        this.content = content;
        setId(id);
    }

    public BlogItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id+1;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            this.creationDate = format.parse(creationDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
