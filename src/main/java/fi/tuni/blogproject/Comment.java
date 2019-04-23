package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for blog post comments.
 *
 * <p>
 *     Automatically makes a database table out of this class using hibernate.
 * </p>
 */
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

    /**
     * Constructor for Comment.
     *
     * @param blogId Id of the BlogItem, the comment is for.
     * @param commentDate Date of the Comment.
     * @param author Name of the Comment author.
     * @param content Content of the comment.
     * @param likes Amount of likes of the Comment.
     */
    public Comment(String blogId, Date commentDate, String author, String content, int likes) {
        this.blogId = blogId;
        this.commentDate = commentDate;
        this.author = author;
        this.content = content;
        this.likes = likes;
    }

    /**
     * Default constructor for the Comment.
     */
    public Comment() {}

    /**
     * Gets the id of the Comment.
     *
     * @return Id of the Comment.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the id of the BlogItem, the Comment is posted for.
     *
     * @return Id of the Comments BlogItem.
     */
    public String getBlogId() {
        return blogId;
    }

    /**
     * Sets the BlogItem id for the Comment.
     *
     * @param blogId Id of the Comments BlogItem.
     */
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    /**
     * Gets the date of Comment creation.
     *
     * @return Creation date of the Comment.
     */
    public Date getCommentDate() {
        return commentDate;
    }

    /**
     * Sets the date for Comment creation.
     *
     * @param commentDate Creation date for the Comment.
     */
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * Gets the author of the Comment.
     *
     * @return Name of the Comment author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the Comment.
     *
     * @param author Name of the Comment author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the Comment content.
     *
     * @return Content of the Comment.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content for Comment.
     *
     * @param content Content to be assigned for Comment.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the amount of like of the Comment.
     *
     * @return Amount of Comment likes.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Changes the amount of Comment likes.
     *
     * @param like Amount, the Comment likes will change.
     */
    public void setLike(int like) {
        this.likes = this.likes + like;
    }
}
