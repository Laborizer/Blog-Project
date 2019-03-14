package fi.tuni.blogproject;

public class BlogItem {

    private static long numberOfBlogPosts;
    long id;
    String title;
    String content;

    public BlogItem(long id, String title, String content) {
        numberOfBlogPosts++;
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
