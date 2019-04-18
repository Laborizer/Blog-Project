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
	}

	public void createTables() {
        jdbcTemplate.execute("CREATE TABLE blogs(id int, creationDate date, author varchar(255)," +
                "title varchar(255), content text)");

        jdbcTemplate.execute("CREATE TABLE comments(id int, blogId int, commentDate date, author varchar(255)," +
                "content text, likes int)");
    }
}
