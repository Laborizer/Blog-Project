package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class BlogItemController {
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
    @Transactional
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
}
