package fi.tuni.blogproject;

import java.util.Date;

public class Comment {
    Long blogId;
    Date commentDate;
    String author;
    String content;

    public Comment(Long blogId, Date commentDate, String author, String content) {
        setBlogId(blogId);
        this.commentDate = commentDate;
        this.author = author;
        this.content = content;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId+1;
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
}
