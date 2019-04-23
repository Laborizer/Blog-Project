package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Class for the BlogItems tags.
 *
 * <p>
 *     Automatically makes a database table out of this class using hibernate.
 * </p>
 */
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(generator = IDGenerator.GENERATOR_NAME)
    @GenericGenerator(
            name = IDGenerator.GENERATOR_NAME,
            strategy = "fi.tuni.blogproject.IDGenerator")
    private String id;

    @Column(name = "blogId")
    String blogId;

    @Column(name = "tagName")
    String tagName;

    /**
     * Constructor for the Tag.
     *
     * @param tagName Name of the Tag.
     * @param blogId Id of the BlogItem, the Tag is assigned for.
     */
    public Tag(String tagName, String blogId) {
        this.tagName = tagName;
        this.blogId = blogId;
    }

    /**
     * Default Tag constructor.
     */
    public Tag() {}

    /**
     * Gets the Tag id.
     *
     * @return Id of the Tag.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the id of the BlogItem, the Tag is assigned for.
     *
     * @return Id of the Tags BlogItem.
     */
    public String getBlogId() {
        return blogId;
    }

    /**
     * Sets the id for the BlogItem, the Tag will be assigned for.
     *
     * @param blogId Id of the Tags BlogItem.
     */
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    /**
     * Gets the name of the Tag.
     *
     * @return Name of the Tag.
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets the name for the Tag.
     *
     * @param tagName Name to be assigned for Tag.
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
