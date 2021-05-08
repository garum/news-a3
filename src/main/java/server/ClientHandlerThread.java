package server;

import client.Connection;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Article;
import service.ArticleService;
import service.AuthService;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandlerThread  extends Thread {

    private  Socket socket;
    public ClientHandlerThread(Socket socket)
    {
        this.socket=socket;
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
        }
    }
}
