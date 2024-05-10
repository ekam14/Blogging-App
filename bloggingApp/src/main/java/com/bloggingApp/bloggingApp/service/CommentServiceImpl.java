package com.bloggingApp.bloggingApp.service;

import com.bloggingApp.bloggingApp.dao.CommentDAO;
import com.bloggingApp.bloggingApp.entity.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
  private final CommentDAO commentDAO;

  @Autowired
  public CommentServiceImpl(CommentDAO theCommentDAO){
    commentDAO = theCommentDAO;
  }

  @Override
  public void saveComment(Comment theComment) {
    commentDAO.saveComment(theComment);
  }
}
