package client.controller;


import client.Connection;

public class AdminController {
    Connection connection;
    public  AdminController(Connection connection){
        this.connection=connection;
    }

    public void createWrite(String username, String password)
    {
        connection.send("CREATEWRITER");
        connection.send(username);
        connection.send(password);

    }
}
