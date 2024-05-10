package com.bloggingApp.bloggingApp.service;

import com.bloggingApp.bloggingApp.dao.LikeDAO;
import com.bloggingApp.bloggingApp.entity.Like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
  private final LikeDAO likeDAO;

  @Autowired
  public LikeServiceImpl(LikeDAO theLikeDAO){
    likeDAO = theLikeDAO;
  }

  @Override
  public void saveLike(Like theLike) {
    likeDAO.saveLike(theLike);
  }

  @Override
  public boolean likeExistsByUserAndBlog(int userId, int blogId) {
    return likeDAO.likeExistsByUserAndBlog(userId, blogId);
  }
}
