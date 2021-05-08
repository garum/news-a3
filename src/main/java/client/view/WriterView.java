package client.view;

import client.controller.WriterController;
import models.Article;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WriterView {
    private JPanel mainPanel;
    private JList<String> list1;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private List<Article> articleList;
    private JFrame jFrame;
    private WriterController writerController;

    public WriterView() {
        this.jFrame= new JFrame("Writer view");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if(index != -1 ) {
                    Article article = articleList.get(index);
                    ArticleFromView articleFromView = new ArticleFromView(writerController,article);
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArticleFromView articleFromView = new ArticleFromView(writerController);

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if(index != -1 ) {
                    Article article = articleList.get(index);
                    writerController.delete(article);
                    articleList.remove(article);
                }
            }
        });
    }

    public void setWriterController(WriterController writerController) {
        this.writerController = writerController;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
    public void updateArticleList() {
        DefaultListModel<String> demoList = new DefaultListModel<>();
        for (Article article : articleList) {
            demoList.addElement((String) article.getTitle());
        }
        this.list1.setModel(demoList);
    }
}
