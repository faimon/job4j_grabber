package ru.job4j.html;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements rows = doc.select(".postslisttopic");
        Elements date = doc.select(".altCol");
        for (Element td : rows) {
            Element href = td.child(1);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
        }
        for (Element createdDate: date) {
            if (createdDate.hasAttr("style")) {
                System.out.println(createdDate.text());
            }
        }
    }
}