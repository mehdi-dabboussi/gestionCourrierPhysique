package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.ContactExterne;
import com.sharing.entity.SousContactExterne;




@Repository
public class SousContactExterneServiceImpl implements SousContactExterneService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<SousContactExterne> getAllSousContactExterne() {
		return em.createQuery("select s from SousContactExterne s").getResultList();
	}

	@Transactional
	public SousContactExterne findSousContactExterneByNameSurname(String nomSousContactExterne,
			String prenomSousContactExterne) {
		return (SousContactExterne) em
				.createQuery(
						"select s from SousContactExterne s where s.nomSousContactExterne=:nomSousContactExterne"
						+ " and s.prenomSousContactExterne=:prenomSousContactExterne")
				.setParameter("nomSousContactExterne", nomSousContactExterne)
				.setParameter("prenomSousContactExterne", prenomSousContactExterne).getSingleResult();
	}

	@Transactional
	public SousContactExterne findSousContactExterneById(
			long idSousContactExterne) {
		return em.find(SousContactExterne.class, idSousContactExterne);
	}

	@Transactional
	public List<SousContactExterne> getSousContactsByContact(
			ContactExterne contactExterne) {
		return em.createQuery("select s from SousContactExterne s where s.contactExterne= :contactExterne").
				setParameter("contactExterne", contactExterne).getResultList();
	}

}
