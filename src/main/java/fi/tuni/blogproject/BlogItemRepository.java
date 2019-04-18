package fi.tuni.blogproject;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BlogItemRepository extends CrudRepository<BlogItem, Long> {
    Optional<BlogItem> findById(String blogId);
    void deleteById(String blogId);
}
