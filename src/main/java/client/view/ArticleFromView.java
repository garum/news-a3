package client.view;

import client.controller.WriterController;
import models.Article;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticleFromView {
    private JTextField titleText;
    private JTextField authorText;
    private JTextField abstractText;
    private JTextArea bodyText;
    private JButton submitButton;
    private JPanel mainPanel;
    private JFrame jFrame;
    private WriterController writerController;
    public ArticleFromView(WriterController writerController) {
        this.jFrame= new JFrame("Article create");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        this.writerController=writerController;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("create");
                writerController.create(
                        new Article(
                                titleText.getText(),
                                abstractText.getText(),
                                authorText.getText(),
                                bodyText.getText()));
            }
        });
    }

    public ArticleFromView(WriterController writerController,Article article) {
        this.jFrame= new JFrame("Article update");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        this.writerController=writerController;

        titleText.setText(article.getTitle());
        authorText.setText(article.getAuthor());
        abstractText.setText(article.getAbstractContent());
        bodyText.setText(article.getBody());
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("update");

                writerController.update(
                        new Article(
                                article.getId(),
                                titleText.getText(),
                                abstractText.getText(),
                                authorText.getText(),
                                bodyText.getText()));
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
