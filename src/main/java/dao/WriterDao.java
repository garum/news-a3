package dao;

import models.Admin;
import models.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class WriterDao implements Dao<Writer> {
    private SessionFactory sessionFactory;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private Session currentSesion;
    private Transaction transaction;

    public  WriterDao()
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
    public Writer get(long id) {
        return currentSesion.get(Writer.class,id);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Writer> getAll() {
        List<Writer> writers = (List<Writer>) currentSesion.createQuery("from Writer").list();;
        return writers;
    }

    @Override
    public void save(Writer writer) {
        currentSesion.save(writer);

    }

    @Override
    public void update(Writer writer) {
        currentSesion.update(writer);
    }

    @Override
    public void delete(Writer writer) {

        currentSesion.delete(writer);
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
