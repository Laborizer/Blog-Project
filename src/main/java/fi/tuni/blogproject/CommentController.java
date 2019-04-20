package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value="/addComment", method= RequestMethod.POST)
    @ResponseBody
    public Comment addComment(@RequestBody Comment c) {
        c.setLike(0);
        c.setCommentDate(new Date());

        commentRepository.save(c);

        return c;
    }

    @GetMapping("/getAllComments")
    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

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

    @RequestMapping(value="/likeComment/{commentId}", method= RequestMethod.POST)
    @ResponseBody
    public Optional<Comment> likeComment(@PathVariable String commentId) {
        Comment c = commentRepository.findById(commentId).get();
        c.setLike(1);
        commentRepository.save(c);

        return Optional.of(c);
    }
}
