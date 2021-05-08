package client.view;

import client.controller.AdminController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView {
    private JButton createButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel mainPanel;
    private JFrame jFrame;
    private AdminController adminController;
    public AdminView() {
        this.jFrame= new JFrame("ArticleView");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminController.createWrite(textField1.getText(), String.valueOf(passwordField1.getPassword()));
            }
        });
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
