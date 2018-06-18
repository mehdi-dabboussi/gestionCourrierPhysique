package com.sharing.entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {

	
	private long idNotification;
	
	private String typeNotification;
	
	private Courrier courrier;
	
	private User notifiedUser;
	
	private static final long serialVersionUID = 1L;

	public Notification() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdNotification() {
		return this.idNotification;
	}

	public void setIdNotification(long idNotification) {
		this.idNotification = idNotification;
	}   
	
	@ManyToOne
	@JoinColumn(name = "idCourrier")
	public Courrier getCourrier() {
		return courrier;
	}
	public void setCourrier(Courrier courrier) {
		this.courrier = courrier;
	}
	public String getTypeNotification() {
		return typeNotification;
	}
	public void setTypeNotification(String typeNotification) {
		this.typeNotification = typeNotification;
	}
	
	@ManyToOne
	@JoinColumn(name = "id")
	public User getNotifiedUser() {
		return notifiedUser;
	}
	public void setNotifiedUser(User notifiedUser) {
		this.notifiedUser = notifiedUser;
	}
   
	
	
}
