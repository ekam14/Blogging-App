package com.bloggingApp.bloggingApp.service;

import com.bloggingApp.bloggingApp.dao.BlogDAO;
import com.bloggingApp.bloggingApp.entity.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
  private final BlogDAO blogDAO;

  @Autowired
  public BlogServiceImpl(BlogDAO theBlogDAO) {
    blogDAO = theBlogDAO;
  }

  @Override
  public void saveBlog(Blog blog) {
    blogDAO.saveBlog(blog);
  }

  @Override
  public List<Blog> getBlogs(int userId) {
    return blogDAO.getBlogs(userId);
  }

  @Override
  public List<Blog> getAllBlogs() {
    return blogDAO.getAllBlogs();
  }

  @Override
  public Blog getBlogById(int blogId) {
    return blogDAO.getBlogById(blogId);
  }

  @Override
  public void updateBlog(Blog theBlog) {
    blogDAO.updateBlog(theBlog);
  }

  @Override
  public void deleteBlogById(int blogId) {
    blogDAO.deleteBlogById(blogId);
  }
}
