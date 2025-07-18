package com.notevault.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    private boolean used = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, Instant now, User user) {
        this.token = token;
        this.expiryDate = now;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }

    public Instant getExpiryDate() {
        return this.expiryDate;
    }

    public boolean isUsed() {
        return this.used;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
