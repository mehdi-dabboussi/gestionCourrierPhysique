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
	
	private String emetteurTransfert;
	private String destinataireTransfert;
	private String estimation;
	private String dateTransfert;
	
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
	public String getEmetteurTransfert() {
		return emetteurTransfert;
	}
	public void setEmetteurTransfert(String emetteurTransfert) {
		this.emetteurTransfert = emetteurTransfert;
	}
	public String getDestinataireTransfert() {
		return destinataireTransfert;
	}
	public void setDestinataireTransfert(String destinataireTransfert) {
		this.destinataireTransfert = destinataireTransfert;
	}
	public String getEstimation() {
		return estimation;
	}
	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}
	public String getDateTransfert() {
		return dateTransfert;
	}
	public void setDateTransfert(String dateTransfert) {
		this.dateTransfert = dateTransfert;
	}
   
	
	
}
