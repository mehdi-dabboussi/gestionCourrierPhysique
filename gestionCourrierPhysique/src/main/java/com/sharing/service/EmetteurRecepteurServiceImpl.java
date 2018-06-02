package com.sharing.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Emetteur_Recepteur;
import com.sharing.entity.Langue;

@Repository
public class EmetteurRecepteurServiceImpl implements EmetteurRecepteurService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Emetteur_Recepteur findUSerById(long prim) {
		return em.find(Emetteur_Recepteur.class, prim);
	}

}
