package dao;

import models.Admin;
import models.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ArticleDao implements Dao<Article> {

    private SessionFactory sessionFactory;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private Session currentSesion;
    private Transaction transaction;

    public  ArticleDao()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        sessionFactory = session.getSessionFactory();
    }
    public void closeEntityManager()
    {
        entityManager.close();
        entityManagerFactory.close();
        sessionFactory.close();;
    }
    @Override
    public Article get(long id) {
        return currentSesion.get(Article.class,id);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getAll() {
        List<Article> articles = (List<Article>) currentSesion.createQuery("from Article").list();;
        return articles;
    }

    @Override
    public void save(Article article) {
        currentSesion.save(article);

    }

    @Override
    public void update(Article article) {
        currentSesion.update(article);
    }

    @Override
    public void delete(Article article) {

        currentSesion.delete(article);
    }

    public void beginTransaction(){
        currentSesion=sessionFactory.openSession();
        transaction=currentSesion.beginTransaction();
    }
    public void closeTransaction()
    {
        transaction.commit();
        currentSesion.close();
    }

}
