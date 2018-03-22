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

public class SousContactExterne implements Serializable {

	
	private Long idSousContactExterne;
	private String nomSousContactExterne;
	private String prenomSousContactExterne;
	private String telSousContactExterne;
	private String emailSousContactExterne;
	private ContactExterne contactExterne;
	private static final long serialVersionUID = 1L;

	public SousContactExterne() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdSousContactExterne() {
		return this.idSousContactExterne;
	}

	public void setIdSousContactExterne(Long idSousContactExterne) {
		this.idSousContactExterne = idSousContactExterne;
	}   
	public String getNomSousContactExterne() {
		return this.nomSousContactExterne;
	}

	public void setNomSousContactExterne(String nomSousContactExterne) {
		this.nomSousContactExterne = nomSousContactExterne;
	}   
	public String getPrenomSousContactExterne() {
		return this.prenomSousContactExterne;
	}

	public void setPrenomSousContactExterne(String prenomSousContactExterne) {
		this.prenomSousContactExterne = prenomSousContactExterne;
	}   
	public String getTelSousContactExterne() {
		return this.telSousContactExterne;
	}

	public void setTelSousContactExterne(String telSousContactExterne) {
		this.telSousContactExterne = telSousContactExterne;
	}   
	public String getEmailSousContactExterne() {
		return this.emailSousContactExterne;
	}

	public void setEmailSousContactExterne(String emailSousContactExterne) {
		this.emailSousContactExterne = emailSousContactExterne;
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
