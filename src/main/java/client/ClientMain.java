package client;

import client.controller.LoginController;
import client.controller.ReaderController;
import client.view.LoginView;
import client.view.ReaderView;

import java.net.InetAddress;

public class ClientMain {

    public static void main(String[] args) {
        try{
            LoginView loginView= new LoginView();
            ReaderView  readerView = new ReaderView();
            InetAddress serverAdress = InetAddress.getLocalHost();
            Connection connection = new Connection(serverAdress, 3333);

            LoginController loginController = new LoginController(connection);
            ReaderController readerController = new ReaderController(connection);
            loginView.setLoginController(loginController);
            readerView.setReaderController(readerController);
            readerView.populateList();

        }catch(Exception e){System.out.println(e);}
    }
}

