package com.bloggingApp.bloggingApp.service;

import com.bloggingApp.bloggingApp.dao.UserDAO;
import com.bloggingApp.bloggingApp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserDAO userDAO;

  @Autowired
  public UserServiceImpl(UserDAO theUserDAO) {
    userDAO = theUserDAO;
  }

  @Override
  public void addUser(User user) {
    userDAO.addUser(user);
  }

  @Override
  public boolean checkForUser(User user) {
    return userDAO.checkForUser(user);
  }

  @Override
  public boolean checkForEmail(User user) {
    return userDAO.checkForEmail(user);
  }

  @Override
  public User findUserByEmail(String email) {
    return userDAO.findUserByEmail(email);
  }
}
