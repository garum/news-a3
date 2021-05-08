package client.view;

import client.controller.ReaderController;
import models.Article;

import javax.swing.*;
import java.util.List;

public class ReaderView {
    private JPanel mainPanel;
    private JList<String> list1;
    private JButton showButton;
    private JButton loginButton;
    private JFrame jFrame;

    private ReaderController readerController;

    public ReaderView()
    {
        this.jFrame= new JFrame("LoginView");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setReaderController(ReaderController readerController) {
        this.readerController = readerController;
    }

    public void populateList()
    {
        List<Article> articleList=readerController.getArticleList();
        DefaultListModel<String> demoList = new DefaultListModel<>();
        for(Article article:articleList){
                demoList.addElement((String)article.getTitle());
        }
        this.list1.setModel(demoList);

    }
}
