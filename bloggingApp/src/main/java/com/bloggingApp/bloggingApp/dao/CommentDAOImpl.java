package com.bloggingApp.bloggingApp.dao;

import com.bloggingApp.bloggingApp.entity.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CommentDAOImpl implements CommentDAO {
  EntityManager entityManager;

  @Autowired
  public CommentDAOImpl(EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }

  @Override
  @Transactional
  public void saveComment(Comment theComment) {
    entityManager.merge(theComment);
  }
}
