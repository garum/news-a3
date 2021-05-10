package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {

    public Socket socket;

    public ObjectOutputStream out;
    public ObjectInputStream in ;

    public  Connection (InetAddress host, int port) {

        try {

            socket = new Socket(host,port);
            System.out.println(socket.toString());
            System.out.println("try to create out");
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("try to create in");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("success to create in");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Connection (Socket ss)
    {
        this.socket=ss;
        try {
            System.out.println(socket.toString());
            System.out.println("handler:try to create out");
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("handler:try to create in");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("handler:success to create in");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public  Connection (Socket ss,ObjectOutputStream out,ObjectInputStream in)
    {
        this.socket=ss;
        this.out=out;
        this.in=in;
    }

    public void send(String msg)
    {
        if (socket.isConnected()) {
                try {
                    out.flush();
                    out.writeUTF(msg);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public String recvString()
    {
        if (socket.isConnected()) {
            try {
                return in.readUTF();
            } catch (IOException e) {
                System.out.println("Connection is intrerupted");
                //e.printStackTrace();
            }
        }
        return  null;
    }

    public void close()
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
