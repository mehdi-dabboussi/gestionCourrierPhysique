package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TransporteurExterne
 *
 */
@Entity

public class TransporteurExterne implements Serializable {

	
	private Long idTransporteurExterne;
	private String nomTransporteurExterne;
	private String adresseTransporteurExterne;
	private String telTransporteurExterne;
	private String emailTransporteurExterne;
	private String faxTransporteurExterne; 
	private static final long serialVersionUID = 1L;

	public TransporteurExterne() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdTransporteurExterne() {
		return this.idTransporteurExterne;
	}

	public void setIdTransporteurExterne(Long idTransporteurExterne) {
		this.idTransporteurExterne = idTransporteurExterne;
	}   
	public String getNomTransporteurExterne() {
		return this.nomTransporteurExterne;
	}

	public void setNomTransporteurExterne(String nomTransporteurExterne) {
		this.nomTransporteurExterne = nomTransporteurExterne;
	}   
	public String getAdresseTransporteurExterne() {
		return this.adresseTransporteurExterne;
	}

	public void setAdresseTransporteurExterne(String adresseTransporteurExterne) {
		this.adresseTransporteurExterne = adresseTransporteurExterne;
	}   
	public String getTelTransporteurExterne() {
		return this.telTransporteurExterne;
	}

	public void setTelTransporteurExterne(String telTransporteurExterne) {
		this.telTransporteurExterne = telTransporteurExterne;
	}   
	public String getEmailTransporteurExterne() {
		return this.emailTransporteurExterne;
	}

	public void setEmailTransporteurExterne(String emailTransporteurExterne) {
		this.emailTransporteurExterne = emailTransporteurExterne;
	}   
	public String getFaxTransporteurExterne() {
		return this.faxTransporteurExterne;
	}

	public void setFaxTransporteurExterne(String faxTransporteurExterne) {
		this.faxTransporteurExterne = faxTransporteurExterne;
	}
	
	
   
}
