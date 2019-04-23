package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * Rest Controller for modifying BlogItems in the database.
 */
@RestController
public class BlogItemController {
    @Autowired
    BlogItemRepository blogItemRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TagRepository tagRepository;

    /**
     * Adds a new BlogItem to database.
     *
     * @param b BlogItem to be added into database.
     * @return Added BlogItem.
     */
    @RequestMapping(value="/addBlogItem", method= RequestMethod.POST)
    @ResponseBody
    public BlogItem handleRequest(@RequestBody BlogItem b) {
        blogItemRepository.save(b);

        return b;
    }

    /**
     * Deletes a BlogItem from the database.
     *
     * <p>
     *     Before the BlogItem is deleted, first deletes all the Comments and
     *     tags, this this BlogItem has, if there are any.
     * </p>
     *
     * @param blogId Id of the BlogItem to be deleted.
     * @return Deleted BlogItem.
     */
    @DeleteMapping("/deleteBlogItem/{blogId}")
    @Transactional
    public Optional<BlogItem> deleteBlogItem(@PathVariable String blogId) {
        Optional<BlogItem> bi = getBlogItem(blogId);
        if (blogItemRepository.findById(blogId).isPresent()) {
            for (Comment c : commentRepository.findAll()) {
                if (c.getBlogId().equals(blogId)) {
                    commentRepository.deleteById(c.getId());
                }
            }
            for (Tag t : tagRepository.findAll()) {
                if (t.getBlogId().equals(blogId)) {
                    tagRepository.deleteById(t.getId());
                }
            }
            blogItemRepository.deleteById(blogId);
        }
        return bi;
    }

    /**
     * Gets all of the BlogItems in the database.
     *
     * <p>
     *     Makes an ArrayList of all BlogItems and sorts them by date using
     *     BlogItems compareTo -method.
     * </p>
     *
     * @return All BlogItems.
     */
    @GetMapping("/getBlogItems")
    public Iterable<BlogItem> getBlogItems() {
        ArrayList<BlogItem> blogItems = (ArrayList<BlogItem>) blogItemRepository.findAll();

        Collections.sort(blogItems);
        return blogItems;
    }

    /**
     * Gets a single BlogItem from the database.
     *
     * @param blogId Id of the BlogItem to be searched from the database.
     * @return BlogItem with the id used for searching.
     */
    @GetMapping("getBlogItem/{blogId}")
    public Optional<BlogItem> getBlogItem(@PathVariable String blogId) {
        return blogItemRepository.findById(blogId);

    }
}
