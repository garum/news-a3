package client.controller;

import client.Connection;
import com.mysql.cj.log.Log;

public class LoginController {
    Connection connection;

    public LoginController(Connection connection){
        this.connection=connection;
    }

    public void loginButtonAction(String username, String  password){
        connection.send("LOGIN");
        connection.send( username);
        connection.send(password);
        String msg= connection.recvString();
        Integer userType = Integer.valueOf(msg);
        System.out.println(userType);
    }
}
