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
    BlogItemRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE blogs(id int, creationDate date, author varchar(255)," +
				"title varchar(255), content text)");

        BlogItem b = new BlogItem(repository.getSize(), new Date(), "Author", "Title", "Content");

		jdbcTemplate.update(
				"INSERT INTO blogs (id, creationDate, author, title, content) VALUES (?, ?, ?, ?, ?)",
				b.getId(), b.getCreationDate(), b.getAuthor(), b.getTitle(), b.getContent()
		);

        repository.save(b);
	}
}
