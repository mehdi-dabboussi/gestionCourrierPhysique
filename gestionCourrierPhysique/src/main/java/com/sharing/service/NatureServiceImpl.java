package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Nature;


@Repository
public class NatureServiceImpl implements NatureService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<Nature> getAllNature() {
		return em.createQuery("select n from Nature n").getResultList();
	}

	@Transactional
	public Nature findNatureById(long idNature) {
		return em.find(Nature.class, idNature);
	}

}
