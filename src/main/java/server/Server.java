package server;

import com.mysql.cj.jdbc.SuspendableXAConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    ServerSocket serverSocket = null;
    Socket socket = null;
    static final int PORT = 3333;

    void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println("Server started at port :" + PORT);
        Integer i=0;
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            i++;
            System.out.println(i);
            new ClientHandlerThread(socket).start();
        }
    }
}
