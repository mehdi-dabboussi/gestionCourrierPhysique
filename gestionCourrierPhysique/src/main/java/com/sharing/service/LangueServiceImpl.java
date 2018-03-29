package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sharing.entity.Langue;


@Repository
public class LangueServiceImpl implements LangueService {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Langue> getAllLangue() {
		return em.createQuery("select l from Langue l").getResultList();
	}
	@Override
	public Langue findLangueById(long idLangue) {
		return em.find(Langue.class, idLangue);
	}

}
