package com.java.forum.entity;


import jakarta.persistence.*;

import java.time.Instant;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "salt", length = 50)
    private String salt;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "activation_code", length = 100)
    private String activationCode;

    @Column(name = "header_url", length = 200)
    private String headerUrl;



    @Column(name = "create_time")
    private Instant createTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DiscussPost> discussPosts;

    public User() {
    }

    public User(String username, String password, String salt, String email, Integer type, Integer status, String activationCode, String headerUrl, Instant createTime, List<DiscussPost> discussPosts) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.type = type;
        this.status = status;
        this.activationCode = activationCode;
        this.headerUrl = headerUrl;
        this.createTime = createTime;
        this.discussPosts = discussPosts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public List<DiscussPost> getDiscussPosts() {
        return discussPosts;
    }

    public void setDiscussPosts(List<DiscussPost> discussPosts) {
        this.discussPosts = discussPosts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", activationCode='" + activationCode + '\'' +
                ", headerUrl='" + headerUrl + '\'' +
                ", createTime=" + createTime +
                ", discussPosts=" + discussPosts +
                '}';
    }
}