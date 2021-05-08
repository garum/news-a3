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

    public  Connection (InetAddress host, int port) throws IOException {
        socket = new Socket(host,port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public  Connection (Socket ss)
    {
        this.socket=ss;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
