package com.sharing.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GlobalCrudServiceImpl implements GlobalCrudService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Object save(Object object) {
		em.persist(object);
		return object;
	}

	@Transactional
	public Object getObjectById(Object object, long idTypeGarantie) {
		return em.find(object.getClass(), idTypeGarantie);
	}

	@Transactional
	public void update(Object object) {
		em.merge(object);

	}

	@Transactional
	public void remove(Object object, long idObject) {
		//em.remove(em.find(object.getClass(), idObject));
		em.remove(em.contains(object) ? object : em.merge(object));
	}

}
