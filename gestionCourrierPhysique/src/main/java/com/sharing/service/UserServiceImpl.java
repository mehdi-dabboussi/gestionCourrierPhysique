package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Courrier;
import com.sharing.entity.UniteBancaire;
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
		 
		List<User> user =  em
				.createQuery("select u from Emetteur_Recepteur u where u.login=:login")
				.setParameter("login", login).getResultList();
		if (!user.isEmpty())
			  return user.get(0);
		return null;
	}

	@Transactional
	public User findUserByNameSurname(String userName, String surname) {
		return (User) em
				.createQuery(
						"select u from User u where u.nom=:userName and u.surname=:surname")
				.setParameter("userName", userName)
				.setParameter("surname", surname).getSingleResult();
	}

	@Transactional
	public List<User> getUsersByUB(UniteBancaire createdUniteBancaire) {
		return em.createQuery("select u from User u where u.uniteBancaire= :createdUniteBancaire").
				setParameter("createdUniteBancaire", createdUniteBancaire).getResultList();
	}

	@Transactional
	public void removeUBFromUser(UniteBancaire uniteBancaire) {
		List<User> users = getUsersByUB(uniteBancaire);
		for (User user : users){
			user.setUniteBancaire(null);
		}
		
	}

	@Transactional
	public List<Courrier> getCourrierEmetteur(User user) {
		return em.createQuery("select c from Courrier c where c.emetteur.id= :user").
				setParameter("user", user.getId()).getResultList();
	}

	@Transactional
	public List<Courrier> getCourrierDestinataire(User user) {
		return em.createQuery("select c from Courrier c where c.destinataire= :user and c.recu= :recu").
				setParameter("user", user).setParameter("recu", true).
				getResultList();
	}
	
	@Transactional
	public List<Courrier> getCourrierEnAttente(User user) {
		return em.createQuery("select c from Courrier c where c.recu= :recu and c.destinataire.id= :user").
				setParameter("recu", false)
				.setParameter("user",user.getId()).getResultList();
	}

	@Transactional
	public User findUserByEmail(String email) {
		List<User> user =  em
				.createQuery("select u from Emetteur_Recepteur u where u.email=:email")
				.setParameter("email", email).getResultList();
		if (!user.isEmpty())
			  return user.get(0);
		return null;
	}

}
