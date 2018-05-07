package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Transfert;


@Repository
public class TransfertServiceImpl  implements TransfertService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Transfert findTransfertById(long idTransfert) {
		 return em.find(Transfert.class, idTransfert);
		
	}

	@Transactional
	public List<Transfert> getAllTransfert() {
		return em.createQuery("Select * from Transfert").getResultList();
		
		
	}

	@Transactional
	public List<Transfert> getTransfertsByCourrier(long idCourrier) {
		return em.createQuery("select t from Transfert t where t.courrier.idCourrier=:idCourrier").setParameter("idCourrier", idCourrier).getResultList();
		
	}

		
	}

