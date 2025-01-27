package com.java.forum.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "discuss_post", schema = "forum_db")
public class DiscussPost {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", length = 100)
    private String title;

    @Lob
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "type")
    private Integer type;   //0- normal; 1- sticky on top;

    @Column(name = "status")
    private Integer status; //0- normal; 1- highlight; 2- banned;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "comment_count")
    private Integer commentCount;

    @Column(name = "score")
    private Double score;   // to sort the posts

    public DiscussPost() {
    }

    public DiscussPost(User user, String title, String content, Integer type, Integer status, Instant createTime, Integer commentCount, Double score) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.type = type;
        this.status = status;
        this.createTime = createTime;
        this.commentCount = commentCount;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}