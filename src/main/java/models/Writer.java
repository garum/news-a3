package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Writer {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Writer()
    {

    }
    public  Writer(String username, String password)
    {
        this.password = password;
        this.username = username ;
    }
    public  Writer(Long id,String username, String password)
    {
        this.id=id;
        this.password = password;
        this.username = username ;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
