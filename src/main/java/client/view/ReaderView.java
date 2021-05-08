package client.view;

import client.controller.ReaderController;
import models.Article;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReaderView {
    private JPanel mainPanel;
    private JList<String> list1;
    private JButton showButton;
    private JButton loginButton;
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel abstractLabel;
    private JLabel bodyLabel;
    private JFrame jFrame;

    private ReaderController readerController;
    private List<Article> articleList;
    private Article viewArticle;
    public ReaderView()
    {
        this.jFrame= new JFrame("ReaderView");
        jFrame.setContentPane(this.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        viewArticle=new Article();
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if(index != -1 ) {

                    setViewArticle(articleList.get(index));
                    updateArticleView();
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateArticleView()
    {

        titleLabel.setText(viewArticle.getTitle());
        abstractLabel.setText(viewArticle.getAbstractContent());
        authorLabel.setText(viewArticle.getAuthor());
        bodyLabel.setText(viewArticle.getBody());
    }
    public void setReaderController(ReaderController readerController) {
        this.readerController = readerController;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void updateArticleList()
    {
        DefaultListModel<String> demoList = new DefaultListModel<>();
        for(Article article:articleList){
                demoList.addElement((String)article.getTitle());
        }
        this.list1.setModel(demoList);

    }

    public void setViewArticle(Article viewArticle) {
        this.viewArticle = viewArticle;
    }
}
