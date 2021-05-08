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

import java.net.InetAddress;
import java.util.List;

public class ClientMain {

    public static void main(String[] args) {
        try{
            LoginView loginView= new LoginView();
            ReaderView  readerView = new ReaderView();
            AdminView adminView =new AdminView();
            WriterView writerView = new WriterView();

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

            while(true)
            {

            }

        }catch(Exception e){System.out.println(e);}
    }
}

