package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

@Component
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    LinkedList<Comment> commentList = new LinkedList<>();

    @Override
    public Comment save(Comment entity) {
        commentList.add(entity);
        return entity;
    }

    @Override
    public void deleteById(Long aLong) {
        int index = Math.toIntExact(aLong);

        LinkedList<Comment> tempList = new LinkedList<>();

        int newId = 1;
        for (Comment c : commentList) {
            if (c.getBlogId() != index) {
                tempList.add(c);

                jdbcTemplate.update(
                        "UPDATE comments SET id = ? WHERE id = ?",
                        newId ,c.getId()
                );
                newId++;
            }
        }
        commentList.clear();
        commentList = tempList;

        int x = 0;
        for (int i=0; i<commentList.size(); i++) {
            commentList.get(i).setId(x);
            x++;
        }
    }

    @Override
    public long getSize() {
        return commentList.size();
    }

    @Override
    public Iterable<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        Optional<Comment> comment = null;
        for (Comment c : commentList) {
            if (c.getId() == aLong) {
                comment = Optional.ofNullable(c);
            }
        }
        return comment;
    }

    @Override
    public Iterable<Comment> findAll(Long aLong) {
        LinkedList<Comment> comments = new LinkedList<>();

        for (Comment c : commentList) {
            if (c.getBlogId() == aLong) {
                comments.add(c);
            }
        }
        return comments;
    }
}