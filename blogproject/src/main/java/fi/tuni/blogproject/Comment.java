package fi.tuni.blogproject;

import java.util.Date;

public class Comment {
    Long id;
    Long blogId;
    Date commentDate;
    String author;
    String content;
    int likes;

    public Comment(Long id, Long blogId, Date commentDate, String author, String content) {
        setId(id);
        setBlogId(blogId);
        this.commentDate = commentDate;
        this.author = author;
        this.content = content;
        this.likes = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id+1;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
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
