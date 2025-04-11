package com.example.mentalhealth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Avoid using 'user' since it's a reserved keyword
public class User {

    @Id
    private String username;

    private String password;  // Plain text password (can be hashed later)

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
