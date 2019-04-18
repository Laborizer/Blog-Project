package fi.tuni.blogproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;

@Component
public class BlogItemRepositoryImpl implements BlogItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    LinkedList<BlogItem> blogItemList = new LinkedList<>();

    @Override
    public BlogItem save(BlogItem entity) {
        blogItemList.add(entity);
        return entity;
    }

    @Override
    public long getSize() {
        return blogItemList.size();
    }

    @Override
    public void deleteById(Long aLong) {
        int index = Math.toIntExact(aLong);

        LinkedList<BlogItem> tempList = new LinkedList<>();

        int newId = 1;
        for (BlogItem b : blogItemList) {
            if (b.getId() != index) {
                tempList.add(b);

                jdbcTemplate.update(
                        "UPDATE blogs SET id = ? WHERE id = ?",
                        newId ,b.getId()
                );
                newId++;
            }
        }
        blogItemList.clear();
        blogItemList = tempList;

        int x = 0;
        for (int i=0; i<blogItemList.size(); i++) {
            blogItemList.get(i).setId(x);
            x++;
        }
    }

    @Override
    public Iterable<BlogItem> findAll() {
        return blogItemList;
    }

    @Override
    public Optional<BlogItem> findById(Long aLong) {
        Optional<BlogItem> blogItem = null;
        for (BlogItem b : blogItemList) {
            if (b.getId() == aLong) {
                blogItem = Optional.ofNullable(b);
            }
        }
        return blogItem;
    }
}
