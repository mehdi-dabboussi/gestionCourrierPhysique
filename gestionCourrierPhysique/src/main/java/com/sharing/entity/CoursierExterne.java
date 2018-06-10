package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CoursierExterne
 *
 */
@Entity

public class CoursierExterne implements Serializable {

	
	private Long idCoursierExterne;
	private String nomCoursierExterne;
	private String prenomCoursierExterne;
	private String telCoursierExterne;
	private String emailCoursierExterne;
	private TransporteurExterne transporteurExterne;
	private static final long serialVersionUID = 1L;
	
	public CoursierExterne() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCoursierExterne() {
		return this.idCoursierExterne;
	}

	public void setIdCoursierExterne(Long idCoursierExterne) {
		this.idCoursierExterne = idCoursierExterne;
	}   
	public String getNomCoursierExterne() {
		return this.nomCoursierExterne;
	}

	public void setNomCoursierExterne(String nomCoursierExterne) {
		this.nomCoursierExterne = nomCoursierExterne;
	}   
	public String getPrenomCoursierExterne() {
		return this.prenomCoursierExterne;
	}

	public void setPrenomCoursierExterne(String prenomCoursierExterne) {
		this.prenomCoursierExterne = prenomCoursierExterne;
	}   
	public String getTelCoursierExterne() {
		return this.telCoursierExterne;
	}

	public void setTelCoursierExterne(String telCoursierExterne) {
		this.telCoursierExterne = telCoursierExterne;
	}   
	public String getEmailCoursierExterne() {
		return this.emailCoursierExterne;
	}

	public void setEmailCoursierExterne(String emailCoursierExterne) {
		this.emailCoursierExterne = emailCoursierExterne;
	}
   
	@ManyToOne
	@JoinColumn(name = "idTransporteurExterne")
	public TransporteurExterne getTransporteurExterne() {
		return transporteurExterne;
	}

	public void setTransporteurExterne(TransporteurExterne transporteurExterne) {
		this.transporteurExterne = transporteurExterne;
	}
	@Override
	public String toString() {
		return "CoursierExterne [idCoursierExterne=" + idCoursierExterne
				+ ", nomCoursierExterne=" + nomCoursierExterne
				+ ", prenomCoursierExterne=" + prenomCoursierExterne
				+ ", telCoursierExterne=" + telCoursierExterne
				+ ", emailCoursierExterne=" + emailCoursierExterne
				+ ", transporteurExterne=" + transporteurExterne + "]";
	}
	
	
}
