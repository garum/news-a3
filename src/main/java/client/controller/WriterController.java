package client.controller;

import client.Connection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Article;

public class WriterController {
    Connection connection;
    public WriterController(Connection connection)
    {
        this.connection=connection;
    }
    public void update(Article article)
    {
        final ObjectMapper objectMapper =new ObjectMapper();
        try {
            String json= objectMapper.writeValueAsString(article);
            System.out.println(article.toString());
            connection.send("UPDATEARTICLE");
            connection.send(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void create(Article article)
    {
        final ObjectMapper objectMapper =new ObjectMapper();
        try {
            String json= objectMapper.writeValueAsString(article);
            System.out.println(article.toString());
            connection.send("CREATEARTICLE");
            connection.send(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void delete(Article article)
    {
        final ObjectMapper objectMapper =new ObjectMapper();
        try {
            String json= objectMapper.writeValueAsString(article);
            System.out.println(article.toString());
            connection.send("DELETEARTICLE");
            connection.send(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
