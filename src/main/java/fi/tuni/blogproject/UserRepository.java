package fi.tuni.blogproject;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(String userId);
    void deleteById(String userId);
}
