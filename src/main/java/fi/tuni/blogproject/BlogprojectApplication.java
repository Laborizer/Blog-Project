package fi.tuni.blogproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Main class of the backend.
 */
@SpringBootApplication
public class BlogprojectApplication implements CommandLineRunner {
    @Autowired
    BlogItemRepository blogItemRepository;

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

    /**
     * Prints all possible curl commands to console on application start, and makes
     * few sample blog posts.
     *
     * @param args Command line arguments.
     * @throws IOException Exception for failed or interrupted I/O operations.
     */
	@Override
	public void run(String... args) throws IOException {
	    createBlogPosts();

	    for(CurlCommand cc : getCurlCommands()) {
	        System.out.println("\nFunction: " + cc.getName());
	        System.out.println("Command: " + cc.getCommand());
        }
	}

    /**
     * Gets all curl commands listed in the curl.commands.json -file.
     *
     * @return All curl commands for REST testing.
     * @throws IOException Exception for failed or interrupted I/O operations.
     */
	private List<CurlCommand> getCurlCommands() throws IOException {
	    final String resourceFile = "curl.commands.json";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceFile);
        return Arrays.asList(new ObjectMapper().readValue(inputStream, CurlCommand[].class));
    }

    /**
     * Creates sample data to database.
     */
    public void createBlogPosts() {
	    BlogItem b1 = new BlogItem(new Date(), "Tester", "Test", "Sample text");
        BlogItem b2 = new BlogItem(new Date(), "Jester", "Test 2", "Such wow");

        blogItemRepository.save(b1);
        blogItemRepository.save(b2);
    }
}
