package fi.tuni.blogproject;

public class Tag {
    String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag() {}

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
