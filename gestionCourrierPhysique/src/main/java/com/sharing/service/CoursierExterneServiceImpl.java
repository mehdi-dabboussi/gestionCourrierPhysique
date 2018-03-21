package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.CoursierExterne;


@Repository
public class CoursierExterneServiceImpl implements CoursierExterneService {
	
	@PersistenceContext
	EntityManager em;


	@Transactional
	public List<CoursierExterne> getAllCoursierExterne() {
		return em.createQuery("select c from CoursierExterne c").getResultList();
	}

	@Transactional
	public CoursierExterne findCoursierExterneByNameSurname(
			String nomCoursierExterne, String prenomCoursierExterne) {
		return (CoursierExterne) em
				.createQuery(
						"select c from CoursierExterne c where c.nomCoursierExterne=:nomCoursierExterne"
						+ " and c.prenomCoursierExterne=:prenomCoursierExterne")
				.setParameter("nomCoursierExterne", nomCoursierExterne)
				.setParameter("prenomCoursierExterne", prenomCoursierExterne).getSingleResult();
	}

	@Transactional
	public CoursierExterneService findCoursierExterneServiceById(
			long idCoursierExterneService) {
		return em.find(CoursierExterneService.class, idCoursierExterneService);
	}

}
