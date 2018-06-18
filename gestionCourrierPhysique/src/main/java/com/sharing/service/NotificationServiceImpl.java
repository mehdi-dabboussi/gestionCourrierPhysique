package com.sharing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharing.entity.Notification;
import com.sharing.entity.User;


@Repository
public class NotificationServiceImpl implements NotificationService {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public List<Notification> getNotifications(User user) {
		return em.createQuery("select n from Notification n where n.notifiedUser=:user").setParameter("user", user)
				.getResultList();
	}

}
