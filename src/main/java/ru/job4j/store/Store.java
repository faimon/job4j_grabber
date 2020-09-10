package ru.job4j.store;

import java.util.List;

public interface Store {
    void save(Post post);

    List<Post> getAll();
}
