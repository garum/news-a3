package client;

import com.mysql.cj.log.Log;

public class LoginController {
    Connection connection;

    public LoginController(Connection connection){
        this.connection=connection;
    }

    public void loginButtonAction(String username, String  password){
        connection.send("the logg in button was pressed");
        connection.send("username:" + username);
        connection.send("password: "  + password);
    }
}
