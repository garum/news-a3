package client.view;

import client.controller.LoginController;

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
    WriterView writerView;
    AdminView adminView;
    public LoginView(WriterView writerView, AdminView adminView){
        this.writerView=writerView;
        this.adminView=adminView;
        this.jFrame= new JFrame("LoginView");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer userType= loginController.loginButtonAction(textField1.getText(), String.valueOf(passwordField1.getPassword()));
                if(userType == 1 ){
                    adminView.open();
                    close();
                }

                if(userType == 2 ){
                    writerView.open();
                    close();
                }
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

    public void open(){
        jFrame.setVisible(true);
    }
    public void close(){
        jFrame.setVisible(false);
    }
}
