package com.sharing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mockito.internal.matchers.Find;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Bordereau;
import com.sharing.entity.ContactExterne;
import com.sharing.entity.UniteBancaire;


@Repository
public class BordereauServiceImpl implements BordereauService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Bordereau findBordereauById(long idBordereau) {
		return em.find(Bordereau.class, idBordereau);
	}

	@Transactional
	public List<Bordereau> getAllBordereau() {
		return em.createQuery("select b from Bordereau b").getResultList();
	}

	@Transactional
	public TreeSet<String> getAllVille() {
		TreeSet<String> set = new TreeSet<String>();
		List<UniteBancaire> listUnite = em.createQuery("select u from UniteBancaire u").getResultList();
		List<ContactExterne> listContact = em.createQuery("select c from ContactExterne c").getResultList();
		
		for (UniteBancaire uniteBancaire: listUnite)
			set.add(uniteBancaire.getVille());
		
		for (ContactExterne contactExterne: listContact)
			set.add(contactExterne.getVille());
		
		return set;
	}

}
