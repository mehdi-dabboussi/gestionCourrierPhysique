package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SousContactExterne
 *
 */
@Entity

public class SousContactExterne extends Emetteur_Recepteur implements Serializable {
	
	private ContactExterne contactExterne;
	private static final long serialVersionUID = 1L;

	public SousContactExterne() {
		super();
	}   
	
	@ManyToOne
	@JoinColumn(name = "idContactExterne")
	public ContactExterne getContactExterne() {
		return contactExterne;
	}
	
	public void setContactExterne(ContactExterne contactExterne) {
		this.contactExterne = contactExterne;
	}
   
}
