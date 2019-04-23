package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for Blog post items.
 *
 * <p>
 *     Automatically makes a database table out of this class using hibernate.
 * </p>
 */
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

    @Column(name = "content", columnDefinition = "TEXT")
    String content;

    /**
     * Constructor for BlogItem.
     *
     * @param creationDate Date, the post was created.
     * @param author Name of the post author.
     * @param title Title of the post.
     * @param content Blog post content.
     */
    public BlogItem(Date creationDate, String author, String title, String content) {
        this.creationDate = creationDate;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    /**
     * Default constructor for BlogItem.
     */
    public BlogItem() {
    }

    /**
     * Gets the BLogItem id.
     *
     * @return Id of the BlogItem.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the creation date of the BlogItem.
     *
     * @return Date of BlogItem creation.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date for BlogItem.
     *
     * @param creationDate Creating date to be assigned for BlogItem.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the author of the BlogItem.
     *
     * @return Name of the BlogItem author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author for BlogItem.
     *
     * @param author Name of the BlogItem author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the BlogItem title.
     *
     * @return Title of the BlogItem.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for BlogItem.
     *
     * @param title Title to be assigned for BlogItem.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the BlogItem.
     *
     * @return Content of the BlogItem.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content for the BlogItem.
     *
     * @param content Content to be assigned for BlogItem.
     */
    public void setContent(String content) {
        this.content = content;
    }
}
