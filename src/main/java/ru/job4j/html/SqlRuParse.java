package ru.job4j.html;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.job4j.grabber.Post;

import java.io.IOException;

public class SqlRuParse {
    public Post loadDescriptionFromUrl(String url) throws IOException {
        Post post = new Post();
        Document doc = Jsoup.connect(url).get();
        Element topic = doc.getElementsByClass("messageHeader").first();
        Element description = doc.getElementsByClass("msgBody").get(1);
        Element createdDate = doc.getElementsByClass("msgFooter").first();
        post.setTopic(topic.text());
        post.setDescription(description.text());
        post.setUrl(url);
        post.setCreateDate(ParseDate.convertToCalendarFormat(createdDate.text()));
        return post;
    }

    public static void main(String[] args) throws IOException {
        SqlRuParse sqlRuParse = new SqlRuParse();
        Post post = sqlRuParse.loadDescriptionFromUrl("https://www.sql.ru/forum/1328685/vakansiya-oracle-dba");
        System.out.println(post);
    }
}