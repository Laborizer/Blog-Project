package fi.tuni.blogproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BlogprojectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    for(CurlCommand cc : getCurlCommands()) {
	        System.out.println("Function: " + cc.getName());
	        System.out.println("Command: " + cc.getCommand() + "\n");
        }
	}

	private List<CurlCommand> getCurlCommands() throws IOException {
	    final String resourceFile = "curl.commands.json";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceFile);
        return Arrays.asList(new ObjectMapper().readValue(inputStream, CurlCommand[].class));
    }
}
