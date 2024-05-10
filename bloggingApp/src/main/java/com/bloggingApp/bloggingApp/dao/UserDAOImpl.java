package com.bloggingApp.bloggingApp.dao;

import com.bloggingApp.bloggingApp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {
  EntityManager entityManager;

  public UserDAOImpl() {

  }

  @Autowired
  public UserDAOImpl(EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }

  @Override
  @Transactional
  public void addUser(User user) {
    entityManager.persist(user);
  }

  @Override
  public boolean checkForUser(User user) {
    TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE " +
            "u.email = :email AND u.password = :password", User.class);

    query.setParameter("email", user.getEmail());
    query.setParameter("password", user.getPassword());

    return !query.getResultList().isEmpty();
  }

  @Override
  public boolean checkForEmail(User user) {
    TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE " +
            "u.email = :email", User.class);

    query.setParameter("email", user.getEmail());

    return !query.getResultList().isEmpty();
  }

  @Override
  public User findUserByEmail(String email) {
    TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE " +
            "u.email = :email", User.class);

    query.setParameter("email", email);

    return query.getSingleResult();
  }
}
