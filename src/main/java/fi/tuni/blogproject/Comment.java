package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(generator = IDGenerator.GENERATOR_NAME)
    @GenericGenerator(
            name = IDGenerator.GENERATOR_NAME,
            strategy = "fi.tuni.blogproject.IDGenerator")
    private String id;

    @Column(name = "blogId")
    String blogId;

    @Column(name = "commentDate")
    Date commentDate;

    @Column(name = "author")
    String author;

    @Column(name = "content")
    String content;

    @Column(name = "likes")
    int likes;

    public Comment(String blogId, Date commentDate, String author, String content, int likes) {
        this.blogId = blogId;
        this.commentDate = commentDate;
        this.author = author;
        this.content = content;
        this.likes = likes;
    }

    public Comment() {}

    public String getId() {
        return id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLike(int like) {
        this.likes = this.likes + like;
    }
}
