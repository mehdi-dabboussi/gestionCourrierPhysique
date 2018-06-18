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
	private String textNotification;
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
	public String getTextNotification() {
		return this.textNotification;
	}

	public void setTextNotification(String textNotification) {
		this.textNotification = textNotification;
	}
   
}
