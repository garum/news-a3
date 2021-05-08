package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin {
    private Long id;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Admin()
    {

    }
    public  Admin(String username, String password)
    {
        this.password = password;
        this.username = username ;
    }
    public  Admin(Long id,String username, String password)
    {
        this.id=id;
        this.password = password;
        this.username = username ;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
