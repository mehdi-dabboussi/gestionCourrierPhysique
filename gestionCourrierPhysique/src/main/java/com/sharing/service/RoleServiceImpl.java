package com.sharing.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sharing.entity.Role;
@Repository
public class RoleServiceImpl implements RoleService {
	@PersistenceContext
	EntityManager em;

	@Override
	public Role findByName(String roleName) {
		return (Role) em.createQuery("select r from Role r where r.name=:name").setParameter("name", roleName).getSingleResult();
	}

}
