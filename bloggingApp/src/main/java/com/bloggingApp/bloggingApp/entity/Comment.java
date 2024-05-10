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
@Table(name = "comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "commentid")
  private int commentId;

  @ManyToOne
  @JoinColumn(name="blogid")
  private Blog blog;

  @ManyToOne
  @JoinColumn(name="userid")
  private User user;

  @Column(name = "content")
  private String content;

  public Comment() {

  }

  public Comment(String content) {
    this.content = content;
  }

  public int getCommentId() {
    return commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Comment{" +
            "commentId=" + commentId +
            ", content='" + content + '\'' +
            '}';
  }
}
