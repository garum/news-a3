package server;

import client.Connection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.WriterDao;
import models.Article;
import models.Writer;
import observer.EventSource;
import service.ArticleService;
import service.AuthService;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandlerThread  extends Thread {

    private  Socket socket;
    private EventSource eventSource;
    public ClientHandlerThread(Socket socket,EventSource eventSource)
    {
        this.socket=socket;
        this.eventSource=eventSource;
    }

    @Override
    public void run() {

        Connection connection = new Connection(this.socket);
        System.out.println("Server: Connections established with Client");
        String line;
        while (true) {
            line = connection.recvString();
            System.out.println("Server: from client " + line);
            if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                System.out.println("Server: Connections closing with Client");
                connection.close();
                return;
            }

            if(line.equals("LOGIN")){
                System.out.println("Login info waiting");
                String username = connection.recvString();
                System.out.println(username);
                String password =connection.recvString();
                System.out.println(password);
                AuthService authService = new AuthService();
                Integer userType = authService.login(username,password);
                System.out.println(userType);
                connection.send(userType.toString());
            }
            if(line.equals("ARTICLES"))
            {
                ArticleService articleService = new ArticleService();
                List<Article> articles = articleService.getAllArticles();
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                final ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.writeValue(out, articles);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final byte[] data = out.toByteArray();
                String json=new String(data);
                System.out.println(json);
                connection.send(json);
            }

            if(line.equals("CREATEWRITER")){
                String username=connection.recvString();
                String password= connection.recvString();
                AuthService authService =new AuthService();
                authService.createWriter(username,password);
            }


            if(line.equals("CREATEARTICLE")){
                String json=connection.recvString();
                final ObjectMapper mapper = new ObjectMapper();
                try {
                    Article article =mapper.readValue(json,Article.class);
                    ArticleService articleService = new ArticleService();
                    articleService.create(article);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                eventSource.notifyObservers("create");
            }
            if(line.equals("UPDATEARTICLE")){

                String json=connection.recvString();
                final ObjectMapper mapper = new ObjectMapper();
                try {
                    Article article =mapper.readValue(json,Article.class);
                    ArticleService articleService = new ArticleService();
                    articleService.update(article);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                eventSource.notifyObservers("update");
            }
            if(line.equals("DELETEARTICLE")){
                String json=connection.recvString();
                final ObjectMapper mapper = new ObjectMapper();
                try {
                    Article article =mapper.readValue(json,Article.class);
                    ArticleService articleService = new ArticleService();
                    articleService.delete(article);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                eventSource.notifyObservers("delete");
            }
        }
    }
}
