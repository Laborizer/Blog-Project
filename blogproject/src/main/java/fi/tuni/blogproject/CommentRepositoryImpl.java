package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
        for (Comment c : commentList) {
            if (c.getBlogId() == aLong) {
                commentList.remove(aLong);
            }
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
        return Optional.empty();
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