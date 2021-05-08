package client;

import client.view.LoginView;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ClientMain {

    public static void main(String[] args) {
        try{
            LoginView longView= new LoginView();

            InetAddress serverAdress = InetAddress.getLocalHost();
            Connection connection = new Connection(serverAdress, 3333);
            connection.send("hello server");
            String msg = connection.recvString();
            System.out.println("Client: " + msg);

            LoginController loginController = new LoginController(connection);
            longView.setLoginController(loginController);

        }catch(Exception e){System.out.println(e);}
    }
}

