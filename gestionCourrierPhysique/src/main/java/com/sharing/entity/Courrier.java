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

	
	private long idCourrier;
	private String etatCourrier;
	private String objetCourrier;
	private String detailsCourrier;
	private String dateCreationCourrier;
	private Nature nature;
	private Langue langue;
	
	private User emetteurUser;
	private User destinataireUser;
	
	private UniteBancaire emetteurUnite;
	private UniteBancaire destinataireUnite;
	
	private ContactExterne emetteurContact;
	private ContactExterne destinataireContact;
	
	private static final long serialVersionUID = 1L;

	public Courrier() {
		super();
	}   
	
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdCourrier() {
		return this.idCourrier;
	}

	public void setIdCourrier(long idCourrier) {
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
	public String getDateCreationCourrier() {
		return this.dateCreationCourrier;
	}

	public void setDateCreationCourrier(String dateCreationCourrier) {
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
	
	@ManyToOne
	@JoinColumn(name = "idUserEmetteur")
	public User getEmetteurUser() {
		return emetteurUser;
	}
	public void setEmetteurUser(User emetteurUser) {
		this.emetteurUser = emetteurUser;
	}
	
	@ManyToOne
	@JoinColumn(name = "idUserDestinataire")
	public User getDestinataireUser() {
		return destinataireUser;
	}
	public void setDestinataireUser(User destinataireUser) {
		this.destinataireUser = destinataireUser;
	}
	
	@ManyToOne
	@JoinColumn(name = "idUniteBancaireEmetteur")
	public UniteBancaire getEmetteurUnite() {
		return emetteurUnite;
	}
	public void setEmetteurUnite(UniteBancaire emetteurUnite) {
		this.emetteurUnite = emetteurUnite;
	}
	
	@ManyToOne
	@JoinColumn(name = "idUniteBancaireDestinataire")
	public UniteBancaire getDestinataireUnite() {
		return destinataireUnite;
	}
	public void setDestinataireUnite(UniteBancaire destinataireUnite) {
		this.destinataireUnite = destinataireUnite;
	}
	
	@ManyToOne
	@JoinColumn(name = "idContactExterneEmetteur")
	public ContactExterne getEmetteurContact() {
		return emetteurContact;
	}
	public void setEmetteurContact(ContactExterne emetteurContact) {
		this.emetteurContact = emetteurContact;
	}
	
	@ManyToOne
	@JoinColumn(name = "idContactExterneDestinataire")
	public ContactExterne getDestinataireContact() {
		return destinataireContact;
	}
	public void setDestinataireContact(ContactExterne destinataireContact) {
		this.destinataireContact = destinataireContact;
	}

	@Override
	public String toString() {
		return "Courrier [idCourrier=" + idCourrier + ", etatCourrier="
				+ etatCourrier + ", objetCourrier=" + objetCourrier
				+ ", detailsCourrier=" + detailsCourrier
				+ ", dateCreationCourrier=" + dateCreationCourrier
				+ ", nature=" + nature + ", langue=" + langue
				+ ", emetteurUser=" + emetteurUser + ", destinataireUser="
				+ destinataireUser + ", emetteurUnite=" + emetteurUnite
				+ ", destinataireUnite=" + destinataireUnite
				+ ", emetteurContact=" + emetteurContact
				+ ", destinataireContact=" + destinataireContact + "]";
	}
	
	
   
}
