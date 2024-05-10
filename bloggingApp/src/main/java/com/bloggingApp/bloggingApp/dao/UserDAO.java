package com.bloggingApp.bloggingApp.dao;

import com.bloggingApp.bloggingApp.entity.User;

public interface UserDAO {
  void addUser(User user);
  boolean checkForUser(User user);
  boolean checkForEmail(User user);
  User findUserByEmail(String email);
}
