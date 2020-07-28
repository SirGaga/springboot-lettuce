package com.zhangjie.lettuce.domain;

public class Article {

    private String id;
    private String title;
    private String content;
    private String author;
    private String createDate;
    private Integer clickNum;

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Article() {
    }

    public Article(String id, String title, String content, String author, String createDate, Integer clickNum) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.clickNum = clickNum;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createDate='" + createDate + '\'' +
                ", clickNum=" + clickNum +
                '}';
    }
}
