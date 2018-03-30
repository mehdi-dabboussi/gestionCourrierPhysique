package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Courrier
 *
 */
@Entity

public class Courrier implements Serializable {

	
	private Long idCourrier;
	private String etatCourrier;
	private String objetCourrier;
	private String detailsCourrier;
	private Date dateCreationCourrier;
	private Nature nature;
	private Langue langue;
	private static final long serialVersionUID = 1L;

	public Courrier() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCourrier() {
		return this.idCourrier;
	}

	public void setIdCourrier(Long idCourrier) {
		this.idCourrier = idCourrier;
	}   
	public String getEtatCourrier() {
		return this.etatCourrier;
	}

	public void setEtatCourrier(String etatCourrier) {
		this.etatCourrier = etatCourrier;
	}   
	public String getObjetCourrier() {
		return this.objetCourrier;
	}

	public void setObjetCourrier(String objetCourrier) {
		this.objetCourrier = objetCourrier;
	}   
	public String getDetailsCourrier() {
		return this.detailsCourrier;
	}

	public void setDetailsCourrier(String detailsCourrier) {
		this.detailsCourrier = detailsCourrier;
	}   
	public Date getDateCreationCourrier() {
		return this.dateCreationCourrier;
	}

	public void setDateCreationCourrier(Date dateCreationCourrier) {
		this.dateCreationCourrier = dateCreationCourrier;
	}
	
	@ManyToOne
	@JoinColumn(name = "idNature")
	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	
	@ManyToOne
	@JoinColumn(name = "idLangue")
	public Langue getLangue() {
		return langue;
	}
	public void setLangue(Langue langue) {
		this.langue = langue;
	}
	
	
   
}
