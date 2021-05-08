package server;

import client.Connection;

import java.io.*;
import java.net.Socket;

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
            } else {
                connection.send(line);
            }
        }
    }
}
