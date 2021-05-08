package client.view;

import client.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    private JButton loginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel mainPanel;
    private JFrame jFrame;
    private LoginController loginController;

    public LoginView(){
        this.jFrame= new JFrame("LoginView");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.loginButtonAction(textField1.getText(), String.valueOf(passwordField1.getPassword()));
            }
        });
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
