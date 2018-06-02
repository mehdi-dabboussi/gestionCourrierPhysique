package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.ContactExterne;

@Repository
public class ContactExterneServiceImpl implements ContactExterneService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<ContactExterne> getAllContactExterne() {
		return em.createQuery("select c from ContactExterne c").getResultList();
	}

	@Transactional
	public ContactExterne findContactExterneByName(String nom) {
		return (ContactExterne) em
				.createQuery(
						"select c from ContactExterne c where c.nom=:nom")
				.setParameter("nom", nom).getSingleResult();
	}

	@Transactional
	public ContactExterne findContactExterneById(long idContactExterne) {
		return em.find(ContactExterne.class, idContactExterne);
	}

}
