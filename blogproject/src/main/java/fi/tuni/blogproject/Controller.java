package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value="/addBlogItem", method= RequestMethod.POST)
    @ResponseBody
    public BlogItem handleRequest(@RequestBody BlogItem b) {
        b.setId(Math.toIntExact(blogItemRepository.getSize()));
        blogItemRepository.save(b);


        jdbcTemplate.update(
                "INSERT INTO blogs (id, creationDate, author, title, content) " +
                        "VALUES (?, ?, ?, ?, ?)",
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
    public ArrayList<Optional<BlogItem>> getBlogItems() {
        ArrayList<Optional<BlogItem>> list = new ArrayList<>();

        for (long i=1; i<=blogItemRepository.getSize(); i++) {
            list.add(getBlogItem(i));
        }
        return list;
    }

    @GetMapping("getBlogItem/{blogId}")
    public Optional<BlogItem> getBlogItem(@PathVariable Long blogId) {
        String sqlId = "SELECT id FROM blogs WHERE ID=?";
        String sqlCreation = "SELECT creationDate FROM blogs WHERE ID=?";
        String sqlAuthor = "SELECT author FROM blogs WHERE ID=?";
        String sqlTitle = "SELECT title FROM blogs WHERE ID=?";
        String sqlContent = "SELECT content FROM blogs WHERE ID=?";

        long setId = (long) jdbcTemplate.queryForObject(
                sqlId, new Object[] { blogId }, long.class);
        Date setCreation = (Date) jdbcTemplate.queryForObject(
                sqlCreation, new Object[] { blogId }, Date.class);
        String setAuthor = (String) jdbcTemplate.queryForObject(
                sqlAuthor, new Object[] { blogId }, String.class);
        String setTitle = (String) jdbcTemplate.queryForObject(
                sqlTitle, new Object[] { blogId }, String.class);
        String setContent = (String) jdbcTemplate.queryForObject(
                sqlContent, new Object[] { blogId }, String.class);

        BlogItem test = new BlogItem(setId-1, setCreation, setAuthor, setTitle, setContent);

        return Optional.of(test);

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
