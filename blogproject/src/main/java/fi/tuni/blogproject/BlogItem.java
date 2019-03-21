package fi.tuni.blogproject;

public class BlogItem {
    long id;
    String author;
    String title;
    String content;

    public BlogItem(long id, String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
