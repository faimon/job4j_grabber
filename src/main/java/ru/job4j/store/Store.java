package ru.job4j.store;

import java.util.List;

public interface Store {
    boolean save(Post post);

    List<Post> getAll();

    Post findById(int id);
}
