package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    BlogItemRepository database;

    @GetMapping("/addBlogItem/{title}/{content}")
    public BlogItem addBlogItem(@PathVariable String title, @PathVariable String content) {
        BlogItem blogItem = new BlogItem(database.getSize(),title, content);
        database.save(blogItem);
        return blogItem;
    }

    @GetMapping("/deleteBlogItem/{blogId}")
    public void deleteBlogItem(@PathVariable Long blogId) {
        database.deleteById(blogId);
    }

    @GetMapping("/getBlogItems")
    public Iterable<BlogItem> getBlogItems() {
        return database.findAll();
    }

    @GetMapping("getBlogItem/{blogId}")
    public Optional<BlogItem> getBlogItem(@PathVariable Long blogId) {
        return database.findById(blogId);
    }
}
