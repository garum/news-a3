package server;

import dao.AdminDao;
import models.Admin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain  {

    static public void main(String[] args)
    {
        Server server = new Server();
        //server.startServer();

        System.out.println("database query");
        AdminDao adminDao =new AdminDao();
        adminDao.beginTransaction();

        adminDao.delete(adminDao.get(3));
        adminDao.update(new Admin(2L,"userupdated","123"));

        adminDao.closeTransaction();
    }

}
