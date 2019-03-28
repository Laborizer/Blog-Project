package fi.tuni.blogproject;

public interface CommentRepository extends MyCrudRepository<Comment, Long> {
    public Iterable<Comment> findAll(Long aLong);
}
