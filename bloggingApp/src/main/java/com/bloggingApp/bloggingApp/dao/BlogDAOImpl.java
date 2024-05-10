package com.bloggingApp.bloggingApp.dao;

import com.bloggingApp.bloggingApp.entity.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class BlogDAOImpl implements BlogDAO {
  EntityManager entityManager;

  public BlogDAOImpl() {

  }

  @Autowired
  public BlogDAOImpl(EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }

  @Override
  @Transactional
  public void saveBlog(Blog blog) {
    entityManager.merge(blog);
  }

  @Override
  public List<Blog> getBlogs(int userId) {
    TypedQuery<Blog> query = entityManager.createQuery("FROM Blog b WHERE user.userId = :userId " +
                    "ORDER BY b.blogId DESC", Blog.class);
    query.setParameter("userId", userId);
    return query.getResultList();
  }

  @Override
  public List<Blog> getAllBlogs() {
    TypedQuery<Blog> query = entityManager.createQuery("SELECT DISTINCT b FROM " +
            "Blog b LEFT JOIN b.likes LEFT JOIN FETCH b.comments ORDER BY b.blogId DESC",
            Blog.class);
    return query.getResultList();
  }

  @Override
  public Blog getBlogById(int blogId) {
    TypedQuery<Blog> query = entityManager.createQuery("FROM Blog b WHERE b.blogId = :blogId",
            Blog.class);
    query.setParameter("blogId", blogId);
    return query.getSingleResult();
  }

  @Override
  @Transactional
  public void updateBlog(Blog theBlog) {
    entityManager.merge(theBlog);
  }

  @Override
  @Transactional
  public void deleteBlogById(int blogId) {
    TypedQuery<Blog> query = entityManager.createQuery("FROM Blog b WHERE b.blogId = :blogId",
            Blog.class);
    query.setParameter("blogId", blogId);

    Blog theBlog = query.getSingleResult();

    entityManager.remove(theBlog);
  }
}
