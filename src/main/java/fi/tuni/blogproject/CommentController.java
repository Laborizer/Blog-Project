package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Rest Controller for modifying Comments in the database.
 */
@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    /**
     * Adds a new Comment to database.
     *
     * @param c Comment to be added into database.
     * @return Added Comment.
     */
    @RequestMapping(value="/addComment", method= RequestMethod.POST)
    @ResponseBody
    public Comment addComment(@RequestBody Comment c) {
        c.setLike(0);
        c.setCommentDate(new Date());

        commentRepository.save(c);

        return c;
    }

    /**
     * Gets all Comments from the database.
     *
     * @return All Comments from the database.
     */
    @GetMapping("/getAllComments")
    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Gets all Comment for a specific BlogItem.
     *
     * @param blogId Id of the BLogItem, which Comments are going to be retured.
     * @return Comments for the BlogItem.
     */
    @GetMapping("/getComments/{blogId}")
    public Iterable<Comment> getComments(@PathVariable String blogId) {
        ArrayList<Comment> comments = new ArrayList<>();

        for (Comment c : commentRepository.findAll()) {
            if (c.getBlogId().equals(blogId)) {
                comments.add(c);
            }
        }
        return comments;
    }

    /**
     * Changes the Comments like -value.
     *
     * @param commentId Id of the Comment, which likes are going to be modified.
     * @return Liked/disliked Comment.
     */
    @RequestMapping(value="/likeComment/{commentId}", method= RequestMethod.POST)
    @ResponseBody
    public Optional<Comment> likeComment(@PathVariable String commentId) {
        Comment c = commentRepository.findById(commentId).get();
        c.setLike(1);
        commentRepository.save(c);

        return Optional.of(c);
    }
}
