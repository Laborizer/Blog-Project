package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="blogs")
public class BlogItem {
    @Id
    @GeneratedValue(generator = IDGenerator.GENERATOR_NAME)
    @GenericGenerator(
            name = IDGenerator.GENERATOR_NAME,
            strategy = "fi.tuni.blogproject.IDGenerator")
    private String id;

    @Column(name = "creationdate")
    Date creationDate;

    @Column(name = "author")
    String author;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    Tag[] blogTags;

    public BlogItem(Date creationDate, String author, String title, String content, Tag[] blogTags) {
        this.creationDate = creationDate;
        this.author = author;
        this.title = title;
        this.content = content;
        this.blogTags = blogTags;
    }

    public BlogItem() {
    }

    public String getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Tag[] getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(Tag[] blogTags) {
        this.blogTags = blogTags;
    }
}
