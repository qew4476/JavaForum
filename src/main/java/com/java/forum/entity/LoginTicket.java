package com.java.forum.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "login_ticket", schema = "forum_db")
public class LoginTicket {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "ticket", nullable = false, length = 45)
    private String ticket;

    @Column(name = "status")
    private Integer status;

    @Column(name = "expired", nullable = false)
    private Instant expired;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getExpired() {
        return expired;
    }

    public void setExpired(Instant expired) {
        this.expired = expired;
    }

}