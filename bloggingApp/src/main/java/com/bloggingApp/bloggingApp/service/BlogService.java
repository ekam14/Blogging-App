package com.bloggingApp.bloggingApp.service;

import com.bloggingApp.bloggingApp.entity.Blog;

import java.util.List;

public interface BlogService {
  void saveBlog(Blog blog);
  List<Blog> getBlogs(int userId);
  List<Blog> getAllBlogs();
  Blog getBlogById(int blogId);
  void updateBlog(Blog theBlog);
  void deleteBlogById(int blogId);
}
