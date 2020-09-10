package ru.job4j.grabber;

import java.util.Calendar;

public class Post {
    private String topic;
    private String description;
    private String url;
    private Calendar createDate;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Post "
                + "topic = '" + topic + System.lineSeparator()
                + "description = '" + description + System.lineSeparator()
                + "url = '" + url + System.lineSeparator()
                + "createDate = " + createDate.getTime();
    }
}
