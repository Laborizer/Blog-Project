package fi.tuni.blogproject;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findById(String commentId);
    void deleteById(String commentId);
}
