package com.bloggingApp.bloggingApp.dao;

import com.bloggingApp.bloggingApp.entity.Comment;

public interface CommentDAO {
  void saveComment(Comment theComment);
}
