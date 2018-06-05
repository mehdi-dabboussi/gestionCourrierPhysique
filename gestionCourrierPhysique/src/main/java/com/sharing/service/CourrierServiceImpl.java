package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Courrier;


@Repository
public class CourrierServiceImpl implements CourrierService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Courrier findCourrierById(long idCourrier) {
		return em.find(Courrier.class, idCourrier);
	}

	@Override
	public List<Courrier> getAllCourrier() {
		return em.createQuery("select c from Courrier c").getResultList();
	}

	@Override
	public Integer countTotalCourriers() {
		List<Courrier> courriers = em.createQuery("select c from Courrier c").getResultList();
		return courriers.size();
	}

}
