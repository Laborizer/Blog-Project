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
 *
 * @author Jimi Savola - jimi.savola@tuni.fi
 */
@SpringBootApplication
public class BlogprojectApplication implements CommandLineRunner {
    @Autowired
    BlogItemRepository blogItemRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TagRepository tagRepository;

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
	    createBlogPostsAndComments();

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
    public void createBlogPostsAndComments() {
	    BlogItem b1 = new BlogItem(new Date(), "Wikipedia", "Varastettu kuolema", "Varastettu " +
                "kuolema sisältää paljon tyylikokeiluja, minkä vuoksi sen yhtenä tehtävänä on pidetty myös " +
                "erilaisten kuvaus-, kerronta- ja leikkaustekniikoiden testaamista. Tapiovaara sai elokuvaan " +
                "vaikutteita sekä Neuvostoliitosta että Ranskasta. Varastettu kuolema tunnetaan erityisesti " +
                "aikaansa edellä olleesta kuvauksestaan sekä Helsingin kaupunkimiljöön hyödyntämisestä. Elokuva " +
                "innoitti runsaasti myöhempiä ohjaajia, vaikkei se saanutkaan suoranaisia seuraajia.");
        BlogItem b2 = new BlogItem(new Date(), "Jeppe", "Muistikirja",
                "Muistikirjan katoamisesta pelästyneet aktivistit ovat siirtämässä kirjapainoaan, kun " +
                        "asekauppias Claessonin painostamana Manja ottaa yhteyden heihin. Manja vie aktistivit " +
                        "Claessonin luo. He eivät pääse sopimukseen asekaupasta, mihin Claesson vastaa " +
                        "kiristyksellä. Manja tapaa myöhemmin Robertin ja vakuuttaa Claessonin olevan tosissaan." +
                        " Manjan ja Robertin suhde kehittyy läheiseksi, ja Manja ehdottaa aktivisteille, että nämä " +
                        "varastaisivat aseet Claessonilta.");
        blogItemRepository.save(b1);
        blogItemRepository.save(b2);

        Comment c1 = new Comment(b1.getId(), new Date(), "Commenter", "Such wow!", 0);
        Comment c2 = new Comment(b2.getId(), new Date(), "Internet Dude",
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut" +
                        " aliquip ex ea commodo consequat.", 0);
        commentRepository.save(c1);
        commentRepository.save(c2);

        Tag t1 = new Tag("#testtag", b1.getId());
        Tag t2 = new Tag("#funny", b2.getId());
        tagRepository.save(t1);
        tagRepository.save(t2);
    }
}
