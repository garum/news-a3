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

    public void create(Article article){
        ArticleDao articleDao= new ArticleDao();
        articleDao.beginTransaction();
        articleDao.save(article);
        articleDao.closeTransaction();
    }

    public void update(Article article)
    {
        ArticleDao articleDao= new ArticleDao();
        articleDao.beginTransaction();
        articleDao.update(article);
        articleDao.closeTransaction();
    }

    public void delete(Article article)
    {
        ArticleDao articleDao= new ArticleDao();
        articleDao.beginTransaction();
        Article foundArticle =articleDao.get(article.getId());
        articleDao.delete(foundArticle);
        articleDao.closeTransaction();
    }

}
