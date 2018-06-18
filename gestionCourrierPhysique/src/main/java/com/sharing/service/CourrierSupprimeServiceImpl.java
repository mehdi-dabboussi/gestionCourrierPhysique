package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Courrier;
import com.sharing.entity.CourrierSupprime;

@Repository
public class CourrierSupprimeServiceImpl implements CourrierSupprimeService {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<CourrierSupprime> getAll() {
		return em.createQuery("select c from CourrierSupprime c").getResultList();
	}

	@Transactional
	public CourrierSupprime findCourrierSupprimeById(long idCourrierSupprime) {
		return em.find(CourrierSupprime.class, idCourrierSupprime);
	}

}
