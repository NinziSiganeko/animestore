//Admin POJO class
//Author :PV Nakedi
//Date: 04 May 2025


package za.ac.cput.domain;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long userId;

    protected String username;
    protected String password;
    protected String email;

    public User() {
    }

    public User(Long userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }



}


