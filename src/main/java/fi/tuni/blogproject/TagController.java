package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    BlogItemRepository blogItemRepository;

    @RequestMapping(value="/addTags", method = RequestMethod.POST)
    public Tag[] addTags(@RequestBody Tag[] tags){
        boolean duplicateTag = false;
        String tag1 = "";
        String tag2 = "";

        for (Tag t : tags) {
            if (blogItemRepository.findById(t.getBlogId()).isPresent()) {
                for (Tag ta : tagRepository.findAll()) {
                    tag1 = ta.getTagName().replaceAll("[^A-Za-z]+", "").toUpperCase();
                    tag2 = t.getTagName().replaceAll("[^A-Za-z]+", "").toUpperCase();

                    if (tag1.equals(tag2) && ta.getBlogId().equals(t.getBlogId())) {
                        duplicateTag = true;
                    }
                }
                if (!duplicateTag) {
                    tagRepository.save(t);
                }
            }
            duplicateTag = false;
        }
        return tags;
    }
}
