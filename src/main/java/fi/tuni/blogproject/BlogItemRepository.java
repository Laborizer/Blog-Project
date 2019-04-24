package fi.tuni.blogproject;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for BlogItem getting and deletion in the database.
 *
 * @author Jimi Savola - jimi.savola@tuni.fi
 */
public interface BlogItemRepository extends CrudRepository<BlogItem, Long> {
    Optional<BlogItem> findById(String blogId);
    void deleteById(String blogId);
}
