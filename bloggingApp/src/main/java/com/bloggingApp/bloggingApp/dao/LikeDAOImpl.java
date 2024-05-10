package com.bloggingApp.bloggingApp.dao;

import com.bloggingApp.bloggingApp.entity.Like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class LikeDAOImpl implements LikeDAO {
  EntityManager entityManager;

  public LikeDAOImpl() {

  }

  @Autowired
  public LikeDAOImpl(EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }

  @Override
  @Transactional
  public void saveLike(Like theLike) {
    entityManager.merge(theLike);
  }

  @Override
  public boolean likeExistsByUserAndBlog(int userId, int blogId) {
    TypedQuery<Like> query = entityManager.createQuery("FROM Like l WHERE user.userId = :uid " +
            "AND blog.blogId = :bid", Like.class);

    query.setParameter("uid", userId);
    query.setParameter("bid", blogId);

    return !query.getResultList().isEmpty();
  }
}
