package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Courrier;
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

	@Transactional
	public Integer countCourreirArrivé(UniteBancaire uniteBancaire) {
		List<Courrier> courriers = em.createQuery("select c from Courrier c where c.etatCourrier=:arrive and destinataireType=:unite and destinataire=:uniteBancaire").
				setParameter("arrive", "arrive").setParameter("unite", "unite").setParameter("uniteBancaire", uniteBancaire).getResultList();
		return courriers.size();
	}

	@Transactional
	public Integer countCourreirDepart(UniteBancaire uniteBancaire) {
		List<Courrier> courriers =  em.createQuery("select c from Courrier c where c.etatCourrier=:depart and emetteurType=:unite and emetteur=:emetteur").
				setParameter("depart", "depart").setParameter("unite", "unite").setParameter("emetteur", uniteBancaire).getResultList();
		return courriers.size();
	}

	@Transactional
	public Integer countCourrierReci(UniteBancaire uniteBancaire) {
		List<Courrier> courriers = em.createQuery("select c from Courrier c where c.recu=:recu and emetteur=:emetteur").
				setParameter("recu", true).setParameter("emetteur", uniteBancaire).getResultList();
		return courriers.size();
	}

}
