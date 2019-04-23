package fi.tuni.blogproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Main class of the backend.
 */
@SpringBootApplication
public class BlogprojectApplication implements CommandLineRunner {
    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

    /**
     * Prints all possible curl commands to console on application start.
     *
     * @param args Command line arguments.
     * @throws IOException Exception for failed or interrupted I/O operations.
     */
	@Override
	public void run(String... args) throws IOException {
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
}
