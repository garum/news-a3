package server;

import dao.AdminDao;
import dao.ArticleDao;
import dao.WriterDao;
import models.Admin;
import models.Article;
import models.Writer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain  {

    static public void main(String[] args)
    {
        Server server = new Server();
        server.startServer();

    }

}
