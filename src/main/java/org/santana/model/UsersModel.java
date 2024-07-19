package org.santana.model;

import java.time.LocalDate;

import org.santana.annotation.modelAnnotation.PrimaryKey;

public class UsersModel extends Model {


    @PrimaryKey
    private Long userId;
    
    private String username;
    private String email;
    private String password;
    private LocalDate created_date;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.created_date = createdDate;
    }

}
