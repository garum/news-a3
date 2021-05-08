package service;

import dao.ArticleDao;
import models.Article;

import java.util.List;

public class ArticleService {

    public List<Article> getAllArticles()
    {
        ArticleDao articleDao= new ArticleDao();
        articleDao.beginTransaction();
        List<Article> articles = articleDao.getAll();
        articleDao.closeTransaction();
        return articles;
    }
}
