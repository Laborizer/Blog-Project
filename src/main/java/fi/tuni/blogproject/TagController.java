package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for modifying Tags in the database.
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
    public Tag[] addTags(@RequestBody Tag[] tags){
        boolean duplicateTag = false;
        String tag1 = "";
        String tag2 = "";

        for (Tag t : tags) {
            if (blogItemRepository.findById(t.getBlogId()).isPresent()) {
                for (Tag ta : tagRepository.findAll()) {
                    tag1 = ta.getTagName().replaceAll("[^A-Za-z]+", "").toLowerCase();
                    tag2 = t.getTagName().replaceAll("[^A-Za-z]+", "").toLowerCase();

                    if (tag1.equals(tag2) && ta.getBlogId().equals(t.getBlogId())) {
                        duplicateTag = true;
                    }
                }
                if (!duplicateTag) {
                    t.setTagName(t.getTagName().replaceAll("[^A-Za-z]+", "").toLowerCase());
                    tagRepository.save(t);
                }
            }
            duplicateTag = false;
        }
        return tags;
    }

    /**
     * Gets all Tags from the database.
     *
     * @return All Tags from the database.
     */
    @GetMapping("/getAllTags")
    public Iterable<Tag> getAllComments() {
        return tagRepository.findAll();
    }
}
