package client.controller;

import client.Connection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Article;

import java.util.ArrayList;
import java.util.List;

public class ReaderController {

    private Connection connection;

    public  ReaderController(Connection connection){
        this.connection=connection;
    }
    public List<Article> getArticleList()
    {
        connection.send("ARTICLES");
        String articlesJson= connection.recvString();
        ObjectMapper objectMapper =new ObjectMapper();
        System.out.println(articlesJson);
        try {
            List<Article> articles =objectMapper.readValue(articlesJson,new TypeReference<List<Article>>(){});
            return articles;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
