package fi.tuni.blogproject;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for User getting and deletion in the database.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(String userId);
    void deleteById(String userId);
}
