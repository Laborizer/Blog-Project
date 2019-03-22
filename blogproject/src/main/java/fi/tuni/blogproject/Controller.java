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
    CommentRepository commentRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/addBlogItem/{author}/{title}/{content}")
    public BlogItem addBlogItem(@PathVariable String author, @PathVariable String title, @PathVariable String content) {

        BlogItem b = new BlogItem(blogItemRepository.getSize(), new Date(), author, title, content);

        blogItemRepository.save(b);
        jdbcTemplate.update(
                "INSERT INTO blogs (id, creationDate, author, title, content) VALUES (?, ?, ?, ?, ?)",
                b.getId(), b.getCreationDate(), b.getAuthor(), b.getTitle(), b.getContent()
        );

        return b;
    }

    @GetMapping("/deleteBlogItem/{blogId}")
    public void deleteBlogItem(@PathVariable Long blogId) {
        jdbcTemplate.update(
                "DELETE FROM blogs WHERE id=?",
                blogId
        );
        jdbcTemplate.update(
                "DELETE FROM comments WHERE blogId=?",
                blogId);

        commentRepository.deleteById(blogId);
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

    @GetMapping("/addComment/{blogId}/{author}/{content}")
    public Comment addComment(@PathVariable Long blogId, @PathVariable String author, @PathVariable String content) {

        Comment c = new Comment(commentRepository.getSize(), blogId, new Date(), author, content);

        commentRepository.save(c);
        jdbcTemplate.update(
                "INSERT INTO comments (id, blogId, commentDate, author, content, likes) VALUES (?, ?, ?, ?, ?, ?)",
                c.getId(), c.getBlogId(), c.getCommentDate(), c.getAuthor(), c.getContent(), c.getLikes()
        );

        return c;
    }

    @GetMapping("getComments/{blogId}")
    public Iterable<Comment> getComments(@PathVariable Long blogId) {
        return commentRepository.findAll(blogId);
    }

    @GetMapping("likeComment/{commentId}")
    public Optional<Comment> likeComment(@PathVariable Long commentId) {
        Comment c = commentRepository.findById(commentId).get();
        c.setLike(1);

        jdbcTemplate.update(
                "UPDATE comments SET likes = ? WHERE id = ?",
                 c.getLikes() ,commentId
        );
        return commentRepository.findById(commentId);
    }
}
