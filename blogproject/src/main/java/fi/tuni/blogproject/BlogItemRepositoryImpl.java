package fi.tuni.blogproject;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;

@Component
public class BlogItemRepositoryImpl implements BlogItemRepository {
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
        blogItemList.remove(index);

        for (int i=index; i<blogItemList.size(); i++) {
            blogItemList.get(i).setId(blogItemList.get(i).getId() - 1);
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
