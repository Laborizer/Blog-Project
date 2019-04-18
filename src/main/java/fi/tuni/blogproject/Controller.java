package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    BlogItemRepository blogItemRepository;

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value="/addBlogItem", method= RequestMethod.POST)
    @ResponseBody
    public BlogItem handleRequest(@RequestBody BlogItem b) {
        blogItemRepository.save(b);

        return b;
    }

    @DeleteMapping("/deleteBlogItem/{blogId}")
    public void deleteBlogItem(@PathVariable String blogId) {
        for (Comment c : commentRepository.findAll()) {
            if (c.getBlogId().equals(blogId)) {
                commentRepository.deleteById(c.getId());
            }
        }
        blogItemRepository.deleteById(blogId);
    }

    @GetMapping("/getBlogItems")
    public Iterable<BlogItem> getBlogItems() {
        return blogItemRepository.findAll();
    }

    @GetMapping("getBlogItem/{blogId}")
    public Optional<BlogItem> getBlogItem(@PathVariable String blogId) {
        return blogItemRepository.findById(blogId);

    }

    @RequestMapping(value="/addComment", method= RequestMethod.POST)
    @ResponseBody
    public Comment addComment(@RequestBody Comment c) {
        c.setLike(0);
        c.setCommentDate(new Date());

        commentRepository.save(c);

        return c;
    }

    @GetMapping("getComments/{blogId}")
    public Iterable<Comment> getComments(@PathVariable String blogId) {
        ArrayList<Comment> comments = new ArrayList<>();

        for (Comment c : commentRepository.findAll()) {
            if (c.getId().equals(blogId)) {
                comments.add(c);
            }

        }
        return comments;
    }

    @RequestMapping(value="/likeComment", method= RequestMethod.POST)
    @ResponseBody
    public Optional<Comment> likeComment(@RequestBody Comment c) {
        commentRepository.findById(c.getId()).get().setLike(1);

        return commentRepository.findById(c.getId());
    }
}
