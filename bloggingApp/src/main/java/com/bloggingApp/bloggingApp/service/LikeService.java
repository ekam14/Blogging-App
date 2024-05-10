package com.bloggingApp.bloggingApp.service;

import com.bloggingApp.bloggingApp.entity.Like;

public interface LikeService {
  void saveLike(Like theLike);
  boolean likeExistsByUserAndBlog(int userId, int blogId);
}
