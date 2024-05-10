package com.bloggingApp.bloggingApp.service;


import com.bloggingApp.bloggingApp.entity.User;

public interface UserService {
  void addUser(User user);
  boolean checkForUser(User user);
  boolean checkForEmail(User user);
  User findUserByEmail(String email);
}
