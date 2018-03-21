package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ContactExterne
 *
 */
@Entity

public class ContactExterne implements Serializable {

	
	private Long idContactExterne;
	private String nomContactExterne;
	private String adresseContactExterne;
	private String telContactExterne;
	private String emailContactExterne;
	private String faxContactExterne;
	private List<SousContactExterne> sousContactExternes;
	private static final long serialVersionUID = 1L;

	public ContactExterne() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdContactExterne() {
		return this.idContactExterne;
	}

	public void setIdContactExterne(Long idContactExterne) {
		this.idContactExterne = idContactExterne;
	}   
	public String getNomContactExterne() {
		return this.nomContactExterne;
	}

	public void setNomContactExterne(String nomContactExterne) {
		this.nomContactExterne = nomContactExterne;
	}   
	public String getAdresseContactExterne() {
		return this.adresseContactExterne;
	}

	public void setAdresseContactExterne(String adresseContactExterne) {
		this.adresseContactExterne = adresseContactExterne;
	}   
	public String getTelContactExterne() {
		return this.telContactExterne;
	}

	public void setTelContactExterne(String telContactExterne) {
		this.telContactExterne = telContactExterne;
	}   
	public String getEmailContactExterne() {
		return this.emailContactExterne;
	}

	public void setEmailContactExterne(String emailContactExterne) {
		this.emailContactExterne = emailContactExterne;
	}   
	public String getFaxContactExterne() {
		return this.faxContactExterne;
	}

	public void setFaxContactExterne(String faxContactExterne) {
		this.faxContactExterne = faxContactExterne;
	}
	
	@OneToMany(mappedBy = "contactExterne",fetch=FetchType.EAGER)
	public List<SousContactExterne> getSousContactExternes() {
		return this.sousContactExternes;
	}

	public void setSousContactExternes(List<SousContactExterne> sousContactExternes) {
		this.sousContactExternes = sousContactExternes;
	}
   
}
