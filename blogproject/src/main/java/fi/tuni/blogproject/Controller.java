package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    BlogItemRepository blogItemRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/addBlogItem/{author}/{title}/{content}")
    public BlogItem addBlogItem(@PathVariable String author, @PathVariable String title, @PathVariable String content) {

        BlogItem b = new BlogItem(blogItemRepository.getSize(), author, title, content);

        blogItemRepository.save(b);
        jdbcTemplate.update(
                "INSERT INTO blogs (id, author, title, content) VALUES (?, ?, ?, ?)",
                b.getId(), b.getAuthor(), b.getTitle(), b.getContent()
        );

        return b;
    }

    @GetMapping("/deleteBlogItem/{blogId}")
    public void deleteBlogItem(@PathVariable Long blogId) {
        jdbcTemplate.update(
                "DELETE FROM blogs WHERE id=?",
                blogId
        );
        blogItemRepository.deleteById(blogId);
    }

    @GetMapping("/getBlogItems")
    public Iterable<BlogItem> getBlogItems() {
        return blogItemRepository.findAll();
    }

    @GetMapping("getBlogItem/{blogId}")
    public Optional<BlogItem> getBlogItem(@PathVariable Long blogId) {
        return blogItemRepository.findById(blogId);
    }
}
