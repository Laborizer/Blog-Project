package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BlogprojectApplication implements CommandLineRunner {

	@Autowired
    JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE blogs(id int, author varchar(255), title varchar(255), content text)");
	}
}
