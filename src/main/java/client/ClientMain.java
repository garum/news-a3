package client;

import client.controller.AdminController;
import client.controller.LoginController;
import client.controller.ReaderController;
import client.controller.WriterController;
import client.view.AdminView;
import client.view.LoginView;
import client.view.ReaderView;
import client.view.WriterView;
import models.Article;
import models.Writer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClientMain {
    private static ObjectInputStream in;
    public static void main(String[] args) {
        try{
            AdminView adminView =new AdminView();
            WriterView writerView = new WriterView();

            LoginView loginView= new LoginView(writerView,adminView);
            ReaderView  readerView = new ReaderView(loginView);

            InetAddress serverAdress = InetAddress.getLocalHost();

            Connection connection = new Connection(serverAdress, 3333);

            LoginController loginController = new LoginController(connection);
            ReaderController readerController = new ReaderController(connection);
            AdminController adminController = new AdminController(connection);
            WriterController writerController = new WriterController(connection);

            adminView.setAdminController(adminController);
            loginView.setLoginController(loginController);
            readerView.setReaderController(readerController);
            writerView.setWriterController(writerController);

            List<Article> articleList=readerController.getArticleList();

            readerView.setArticleList(articleList);
            readerView.updateArticleList();

            writerView.setArticleList(articleList);
            writerView.updateArticleList();

        }catch(Exception e){System.out.println(e);}
    }
}

