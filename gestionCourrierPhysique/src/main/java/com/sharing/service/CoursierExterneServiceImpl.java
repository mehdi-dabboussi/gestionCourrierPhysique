package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.sql.Delete;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.CoursierExterne;
import com.sharing.entity.SousContactExterne;
import com.sharing.entity.TransporteurExterne;

@Repository
public class CoursierExterneServiceImpl implements CoursierExterneService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<CoursierExterne> getAllCoursierExterne() {
		return em.createQuery("select c from CoursierExterne c")
				.getResultList();
	}

	@Transactional
	public CoursierExterne findCoursierExterneByNameSurname(
			String nomCoursierExterne, String prenomCoursierExterne) {
		return (CoursierExterne) em
				.createQuery(
						"select c from CoursierExterne c where c.nomCoursierExterne=:nomCoursierExterne"
								+ " and c.prenomCoursierExterne=:prenomCoursierExterne")
				.setParameter("nomCoursierExterne", nomCoursierExterne)
				.setParameter("prenomCoursierExterne", prenomCoursierExterne)
				.getSingleResult();
	}

	@Transactional
	public CoursierExterne findCoursierExterneServiceById(
			long idCoursierExterne) {
		return em.find(CoursierExterne.class, idCoursierExterne);
	}

	
	@Transactional
	public List<CoursierExterne> getCoursierExternesByTransporteurExterne(
			TransporteurExterne transporteurExterne) 
			{
		return em.createQuery(
						"select c from CoursierExterne c where c.transporteurExterne= :transporteurExterne")
				.setParameter("transporteurExterne", transporteurExterne).getResultList();
			}
	
	@Transactional
	public void deleteWithTransporteurExterne(TransporteurExterne transporteurExterne)
	{
			List<CoursierExterne> coursierExternes = getCoursierExternesByTransporteurExterne(transporteurExterne);
			for (CoursierExterne coursierExterne : coursierExternes)
			{ deleteCoursierExterne(coursierExterne);
	}
		
			
	}
	@Transactional
	private void deleteCoursierExterne(CoursierExterne coursierExterne) {
			//em.remove(user);
			em.remove(em.contains(coursierExterne) ? coursierExterne : em.merge(coursierExterne));
		}
		
	

}

