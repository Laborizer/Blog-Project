package fi.tuni.blogproject;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for Comment getting and deletion in the database.
 *
 * @author Jimi Savola - jimi.savola@tuni.fi
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findById(String commentId);
    void deleteById(String commentId);
}
