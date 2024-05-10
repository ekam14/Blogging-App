package com.bloggingApp.bloggingApp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="likes")
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "likeid")
  private int likeId;

  @ManyToOne
  @JoinColumn(name="blogid")
  private Blog blog;

  @ManyToOne
  @JoinColumn(name="userid")
  private User user;

  public Like() {

  }

  public int getLikeId() {
    return likeId;
  }

  public void setLikeId(int likeId) {
    this.likeId = likeId;
  }

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Like{" +
            "likeId=" + likeId +
            '}';
  }
}

