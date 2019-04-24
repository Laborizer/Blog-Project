package fi.tuni.blogproject;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for Tag getting and deletion in the database.
 *
 * @author Jimi Savola - jimi.savola@tuni.fi
 */
public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findById(String tagId);
    void deleteById(String tagId);
}
