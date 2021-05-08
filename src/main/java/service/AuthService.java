package service;

import dao.AdminDao;
import dao.WriterDao;
import models.Admin;
import models.Writer;

import java.util.List;

public class AuthService {

    public Integer login(String username,String password ){
        AdminDao adminDao =new AdminDao();
        adminDao.beginTransaction();
        List<Admin> adminList=adminDao.getAll();
        adminDao.closeTransaction();

        for(Admin admin:adminList){
            if( admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                return 1;
            }
        }
        WriterDao writerDao=new WriterDao();
        writerDao.beginTransaction();
        List<Writer> writerList=writerDao.getAll();
        writerDao.closeTransaction();
        System.out.println(writerList.get(0).getUsername());
        for(Writer writer:writerList)
        {
            if(writer.getUsername().equals(username) && writer.getPassword().equals(password)){
                return  2;
            }
        }
        return 0;
    }

    public void createWriter(String username, String password)
    {
        WriterDao writerDao=new WriterDao();
        writerDao.beginTransaction();
        writerDao.save(new Writer(username,password));
        writerDao.closeTransaction();
    }
}
