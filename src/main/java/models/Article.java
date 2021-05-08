package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="abstractContent")
    private String abstractContent;
    @Column(name="author")
    private String author;

    @Column(name="body")
    private String body;


    public Article()
    {

    }
    public  Article(String title, String abstractContent,String author, String body)
    {
        this.title=title;
        this.author=author;
        this.abstractContent=abstractContent;
        this.body=body;
    }
    public  Article(Long id, String title, String abstractContent,String author, String body)
    {
        this.id=id;
        this.title=title;
        this.author=author;
        this.abstractContent=abstractContent;
        this.body=body;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
