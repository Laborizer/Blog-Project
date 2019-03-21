package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

@SpringBootApplication
public class BlogprojectApplication implements CommandLineRunner {

	@Autowired
    JdbcTemplate jdbcTemplate;

	@Autowired
    BlogItemRepository blogItemRepository;

	@Autowired
    CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        createTables();

        BlogItem b = new BlogItem(blogItemRepository.getSize(), new Date(), "Author", "Title", "Content");
        Comment c = new Comment((long) 1, new Date(), "Commenter", "Good post!");

		jdbcTemplate.update(
				"INSERT INTO blogs (id, creationDate, author, title, content) VALUES (?, ?, ?, ?, ?)",
				b.getId(), b.getCreationDate(), b.getAuthor(), b.getTitle(), b.getContent()
		);
        blogItemRepository.save(b);

        jdbcTemplate.update(
                "INSERT INTO comments (blogId, commentDate, author, content) VALUES (?, ?, ?, ?)",
                c.getBlogId(), c.getCommentDate(), c.getAuthor(), c.getContent()
        );
        commentRepository.save(c);
	}

	public void createTables() {
        jdbcTemplate.execute("CREATE TABLE blogs(id int, creationDate date, author varchar(255)," +
                "title varchar(255), content text)");

        jdbcTemplate.execute("CREATE TABLE comments(blogId int, commentDate date, author varchar(255)," +
                "content text)");
    }
}
