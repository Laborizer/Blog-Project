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

    @DeleteMapping("/deleteBlogItem/{blogId}")
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

        Comment c = new Comment(commentRepository.getSize(), blogId, new Date(), author, content, 0);

        commentRepository.save(c);
        jdbcTemplate.update(
                "INSERT INTO comments (id, blogId, commentDate, author, content, likes) VALUES (?, ?, ?, ?, ?, ?)",
                c.getId(), c.getBlogId(), c.getCommentDate(), c.getAuthor(), c.getContent(), c.getLikes()
        );

        return c;
    }

    @GetMapping("getComments/{blogId}")
    public Iterable<Comment> getComments(@PathVariable Long blogId) {
        ArrayList<Comment> comments = new ArrayList<>();

        for (int i=1; i<=commentRepository.getSize(); i++) {
            String sqlBlogId = "SELECT blogId FROM comments WHERE ID=?";

            long setBlogId = (long) jdbcTemplate.queryForObject(
                    sqlBlogId, new Object[] { i }, long.class);

            if (setBlogId == blogId) {
                String sqlId = "SELECT id FROM comments WHERE ID=?";
                String sqlDate = "SELECT commentDate FROM comments WHERE ID=?";
                String sqlAuthor = "SELECT author FROM comments WHERE ID=?";
                String sqlContent = "SELECT content FROM comments WHERE ID=?";
                String sqlLikes = "SELECT likes FROM comments WHERE ID=?";

                long setId = (long) jdbcTemplate.queryForObject(
                        sqlId, new Object[] { i }, long.class);
                Date setDate = (Date) jdbcTemplate.queryForObject(
                        sqlDate, new Object[] { i }, Date.class);
                String setAuthor = (String) jdbcTemplate.queryForObject(
                        sqlAuthor, new Object[] { i }, String.class);
                String setContent = (String) jdbcTemplate.queryForObject(
                        sqlContent, new Object[] { i }, String.class);
                int setLikes = (int) jdbcTemplate.queryForObject(
                        sqlLikes, new Object[] { i }, int.class);

                Comment comment = new Comment(setId-1, setBlogId, setDate, setAuthor, setContent, setLikes);
                comments.add(comment);
            }

        }
        return comments;
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
