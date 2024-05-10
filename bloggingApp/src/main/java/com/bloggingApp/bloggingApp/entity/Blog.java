package com.bloggingApp.bloggingApp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="blogs")
public class Blog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="blogid")
  private int blogId;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                  CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name="userid")
  private User user;

  @Column(name="title")
  private String title;

  @Column(name="content")
  private String content;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog")
  private List<Like> likes;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog")
  private List<Comment> comments;

  public Blog() {

  }

  public Blog(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public int getBlogId() {
    return blogId;
  }

  public void setBlogId(int blogId) {
    this.blogId = blogId;
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


  public List<Like> getLikes() {
    return likes;
  }

  public void setLikes(List<Like> likes) {
    this.likes = likes;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public int getNumberOfLikes() {
    return likes.size();
  }

  public int getNumberOfComments() {
    return comments.size();
  }

  public String getAllComments() {
    StringBuilder allComments = new StringBuilder();

    for(Comment c: comments){
      User user = c.getUser();
      String userName = user.getFirstName() + " " + user.getLastName();
      allComments.append(userName).append(": ").append(c.getContent());
      allComments.append(System.getProperty("line.separator"));
    }
    return allComments.toString();
  }

  @Override
  public String toString() {
    return "Blog{" +
            "blogId=" + blogId +
            ", user=" + user +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", likes=" + likes +
            ", comments=" + comments +
            '}';
  }
}
