package fi.tuni.blogproject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    public Tag(String tagName, String blogId) {
        this.tagName = tagName;
        this.blogId = blogId;
    }

    public Tag() {}

    public String getId() {
        return id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
