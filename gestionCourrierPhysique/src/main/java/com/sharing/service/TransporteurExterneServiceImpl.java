package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.TransporteurExterne;

@Repository
public class TransporteurExterneServiceImpl implements
		TransporteurExterneService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<TransporteurExterne> getAllTransporteurExterne() {
		return em.createQuery("select t from TransporteurExterne t").getResultList();
	}

	@Transactional
	public TransporteurExterne findTransporteurExterneByName(
			String nomTransporteurExterne) {
		return (TransporteurExterne) em
				.createQuery(
						"select t from TransporteurExterne t where t.nomTransporteurExterne=:nomTransporteurExterne")
				.setParameter("nomTransporteurExterne", nomTransporteurExterne).getSingleResult();
	}

	@Transactional
	public TransporteurExterne findTransporteurExterneById(
			long idTransporteurExterne) {
		return em.find(TransporteurExterne.class, idTransporteurExterne);
	}

}
