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
        aLong = aLong-1;

        int index = Math.toIntExact(aLong);
        blogItemList.remove(index);

        for (int i=index; i<blogItemList.size(); i++) {
            blogItemList.get(i).setId(blogItemList.get(i).getId() - 1);

            jdbcTemplate.update(
                    "UPDATE blogs SET id = ? WHERE id = ?",
                    blogItemList.get(i).getId()-1, blogItemList.get(i).getId()
            );
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
