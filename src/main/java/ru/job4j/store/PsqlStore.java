package ru.job4j.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.grabber.SqlRuParse;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class PsqlStore implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("user"),
                    cfg.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(Post post) {
        try (PreparedStatement ps = cnn
                .prepareStatement("INSERT INTO post(name, text, link, created) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, post.getTopic());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getUrl());
            Timestamp timestamp = new Timestamp(post.getCreateDate().getTime().getTime());
            ps.setTimestamp(4, timestamp);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement ps = cnn
                .prepareStatement("SELECT name, text, link, created FROM post")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getTimestamp("created"));
                posts.add(new Post(
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("link"),
                        calendar));
            }
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return posts;
    }

    @Override
    public Post findById(int id) {
        try (PreparedStatement ps = cnn
                .prepareStatement("SELECT name, text, link, created FROM post WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Calendar created = Calendar.getInstance();
            created.setTime(rs.getTimestamp("created"));
            Post post = new Post(rs.getString("name"),
                    rs.getString("text"),
                    rs.getString("link"),
                    created);
            rs.close();
            return post;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) {
        Properties cfg = new Properties();
        try (InputStream in = PsqlStore.class
                .getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
            PsqlStore store = new PsqlStore(cfg);
            SqlRuParse parse = new SqlRuParse();
            for (Post post : parse.list("https://www.sql.ru/forum/job-offers/1")) {
                store.save(post);
            }
            List<Post> out = store.getAll();
            for (Post p : out
            ) {
                System.out.println(p);
            }
            System.out.println("found by id = 2: ");
            System.out.println(store.findById(2));
            store.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
