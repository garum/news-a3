package dao;

import models.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class AdminDao implements Dao<Admin> {

    private SessionFactory sessionFactory;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private Session currentSesion;
    private Transaction transaction;

    public  AdminDao()
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
    public Admin get(long id) {
        Admin admin = currentSesion.get(Admin.class,id);
        return admin;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Admin> getAll() {
        List<Admin> admins = (List<Admin>) currentSesion.createQuery("from Admin").list();;
        return admins;
    }

    @Override
    public void save(Admin admin) {
        currentSesion.save(admin);

    }

    @Override
    public void update(Admin admin) {
        currentSesion.update(admin);
    }

    @Override
    public void delete(Admin admin) {

        currentSesion.delete(admin);
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
