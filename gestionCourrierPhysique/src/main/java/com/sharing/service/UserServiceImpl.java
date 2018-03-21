package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.User;

@Repository
public class UserServiceImpl implements UserService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void CreateUser(User user) {
		em.persist(user);
	}

	@Transactional
	public void updateUser(User user) {
		em.merge(user);
	}

	@Transactional
	public User findUSerById(long idUser) {
		return em.find(User.class, idUser);
	}

	@Transactional
	public void detachUser(User user) {
		em.detach(user);
	}

	@Transactional
	public void deleteUser(User user) {
		//em.remove(user);
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	@Transactional
	public List<User> getAllUsers() {
		return em.createQuery("select u from User u").getResultList();
	}

	@Transactional
	public User findUserByLogin(String login) {
		return (User) em
				.createQuery("select u from User u where u.login=:login")
				.setParameter("login", login).getSingleResult();
	}

	@Transactional
	public User findUserByNameSurname(String userName, String surname) {
		return (User) em
				.createQuery(
						"select u from User u where u.userName=:userName and u.surname=:surname")
				.setParameter("userName", userName)
				.setParameter("surname", surname).getSingleResult();
	}
}
