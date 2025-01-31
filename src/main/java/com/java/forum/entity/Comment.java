package com.java.forum.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "comment", schema = "forum_db")
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "entity_type")
    private Integer entityType; // 1: post, 2: comment

    @Column(name = "entity_id")
    private Integer entityId;   // post id(if entityType=1) or comment id(if entityType=2)

    @Column(name = "target_id")
    private Integer target_id=0;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Instant createTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
                ", target_id=" + target_id +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
    public Comment() {
    }

    public Comment(User user, Integer entityType, Integer entityId, Integer target_id, String content, Integer status, Instant createTime) {
        this.user = user;
        this.entityType = entityType;
        this.entityId = entityId;
        this.target_id = target_id;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
    }
}