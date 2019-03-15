package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BlogprojectApplication implements CommandLineRunner {

    @Autowired
    BlogItemRepository repo;

	@Autowired
    JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE blogs(id int, title varchar(255), content text)");

        BlogItem b1 = new BlogItem(repo.getSize(), "Title",
                "Three to the one to the one to the three, nyt se on auki, nyt se on kii");
        addBlogItem(b1);
	}

	public void addBlogItem(BlogItem b) {
        repo.save(b);
        jdbcTemplate.update(
                "INSERT INTO blogs (id, title, content) VALUES (?, ?, ?)",
                b.getId(), b.getTitle(), b.getContent()
        );
    }
}
