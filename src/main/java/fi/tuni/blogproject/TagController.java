package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Rest Controller for modifying Tags in the database.
 *
 * @author Jimi Savola - jimi.savola@tuni.fi
 */
@RestController
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    BlogItemRepository blogItemRepository;

    /**
     * Adds new Tags to database.
     *
     * <p>
     *     Tag holds a BlogItem id, so the same BlogItem cannot have duplicate
     *     tags on them.
     * </p>
     *
     * @param tags Tags to be added into database.
     * @return Added Tags.
     */
    @RequestMapping(value="/addTags", method = RequestMethod.POST)
    public ArrayList<Tag> addTags(@RequestBody Tag[] tags){
        ArrayList<Tag> returnableTags = new ArrayList<>();
        boolean duplicateTag = false;
        String tag1 = "";
        String tag2 = "";

        for (Tag t : tags) {
            if (blogItemRepository.findById(t.getBlogId()).isPresent()) {
                for (Tag ta : tagRepository.findAll()) {
                    tag1 = ta.getTagName().replaceAll("[^a-zA-Z0-9]+", "").toLowerCase();
                    tag2 = t.getTagName().replaceAll("[^a-zA-Z0-9]+", "").toLowerCase();

                    if (tag1.equals(tag2) && ta.getBlogId().equals(t.getBlogId())) {
                        duplicateTag = true;
                    }
                }
                if (!duplicateTag) {
                    t.setTagName("#" + t.getTagName().replaceAll("[^A-Za-z0-9]+", "").toLowerCase());
                    tagRepository.save(t);
                    returnableTags.add(t);
                }
            }
            tag1 = "";
            tag2 = "";
            duplicateTag = false;
        }
        return returnableTags;
    }

    /**
     * Gets all Tags from the database.
     *
     * @return All Tags from the database.
     */
    @GetMapping("/getAllTags")
    public Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
