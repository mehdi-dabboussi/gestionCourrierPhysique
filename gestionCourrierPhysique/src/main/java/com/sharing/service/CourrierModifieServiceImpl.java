package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Courrier;
import com.sharing.entity.CourrierModifie;
import com.sharing.entity.CourrierSupprime;

@Repository
public class CourrierModifieServiceImpl implements CourrierModifieService {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<CourrierModifie> getAll() {
		return em.createQuery("select c from CourrierModifie c").getResultList();
	}

	@Transactional
	public CourrierModifie findCourrierModifieById(long idCourrierModifie) {
		return em.find(CourrierModifie.class, idCourrierModifie);
	}

}
