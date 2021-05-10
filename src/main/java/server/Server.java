package server;

import client.Connection;
import com.mysql.cj.jdbc.SuspendableXAConnection;
import observer.EventSource;
import observer.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Server implements Observer {
    ServerSocket serverSocket = null;
    ServerSocket eventEmitterServer=null;
    Socket socket = null;
    static final int PORT = 3333;
    static final int PORT2 = 3334;
    
    private List<Connection> eventEmitterConnections = new ArrayList<>();
    void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            eventEmitterServer=new ServerSocket(PORT2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server started at port :" + PORT);
        Integer i=0;
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Server: coonect first" + socket.toString());
                // TimeUnit.SECONDS.sleep(1);
                EventSource eventSource=new EventSource();
                eventSource.addObserver(this);
                new ClientHandlerThread(socket,eventSource).start();

            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            i++;
            System.out.println(i);

        }
    }

    @Override
    public void update(String event) {

        System.out.println("event recieved: " + event);
        for(Connection c:eventEmitterConnections){
            c.send("update");
        }
    }
}
