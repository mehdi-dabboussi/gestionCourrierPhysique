package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.UniteBancaire;


@Repository
public class UniteBancaireServiceImpl implements UniteBancaireService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public UniteBancaire findUniteBancaireById(long idUniteBancaire) {
		return em.find(UniteBancaire.class, idUniteBancaire);
	}

	@Transactional
	public List<UniteBancaire> getAllUniteBancaire() {
		return em.createQuery("select u from UniteBancaire u").getResultList();
	}

	@Transactional
	public UniteBancaire findUniteBancaireByName(String nom) {
		return (UniteBancaire) em
				.createQuery(
						"select u from UniteBancaire u where u.nom=:nom")
				.setParameter("nom", nom).getSingleResult();
	}

}
