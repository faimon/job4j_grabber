package ru.job4j.grabber;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.store.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {
    @Override
    public List<Post> list(String link) {
        List<Post> rsl = new ArrayList<>();
        try {
            String url;
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".postslisttopic");
            for (Element elem : row) {
                url = elem.child(0).attr("href");
                rsl.add(detail(url));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Post detail(String link) {
        Post post = new Post();
        try {
            Document doc = Jsoup.connect(link).get();
            Element topic = doc.getElementsByClass("messageHeader").first();
            Element description = doc.getElementsByClass("msgBody").get(1);
            Element createdDate = doc.getElementsByClass("msgFooter").first();
            post.setTopic(topic.text());
            post.setDescription(description.text());
            post.setUrl(link);
            post.setCreateDate(ParseDate.convertToCalendarFormat(createdDate.text()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static void main(String[] args) {
        SqlRuParse sqlRuParse = new SqlRuParse();
        List<Post> posts = sqlRuParse.list("https://www.sql.ru/forum/job-offers");
        for (Post  pt: posts) {
            System.out.println(pt);
        }
    }
}