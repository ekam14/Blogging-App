package com.bloggingApp.bloggingApp.dao;

import com.bloggingApp.bloggingApp.entity.Like;

public interface LikeDAO {
  void saveLike(Like theLike);
  boolean likeExistsByUserAndBlog(int userId, int blogId);
}
