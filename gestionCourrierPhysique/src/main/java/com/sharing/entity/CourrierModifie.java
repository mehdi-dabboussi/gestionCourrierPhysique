package com.sharing.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CourrierModifie
 *
 */
@Entity

public class CourrierModifie implements Serializable {

	
	private long idCourrier;
	private String etatCourrier;
	private String objetCourrier;
	private String detailsCourrier;
	private String dateCreationCourrier;
	private Nature nature;
	private Langue langue;
	private boolean recu;
	
	
	private String dateModification;
	
	private User actor;
	
	private Emetteur_Recepteur emetteur;
	private Emetteur_Recepteur destinataire;
	
	private String emetteurType;
	private String destinataireType;
	
	private static final long serialVersionUID = 1L;

	public CourrierModifie() {
		super();
	}
   
	
	@Id    
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
	@JoinColumn(name = "idEmetteur")
	public Emetteur_Recepteur getEmetteur() {
		return emetteur;
	}

	public void setEmetteur(Emetteur_Recepteur emetteur) {
		this.emetteur = emetteur;
	}

	
	@ManyToOne
	@JoinColumn(name = "idRecepteur")
	public Emetteur_Recepteur getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Emetteur_Recepteur destinataire) {
		this.destinataire = destinataire;
	}

	public boolean isRecu() {
		return recu;
	}

	public void setRecu(boolean recu) {
		this.recu = recu;
	}

	public String getEmetteurType() {
		return emetteurType;
	}

	public void setEmetteurType(String emetteurType) {
		this.emetteurType = emetteurType;
	}

	public String getDestinataireType() {
		return destinataireType;
	}

	public void setDestinataireType(String destinataireType) {
		this.destinataireType = destinataireType;
	}
	
	

	@Override
	public String toString() {
		return "Courrier [idCourrier=" + idCourrier + ", etatCourrier="
				+ etatCourrier + ", objetCourrier=" + objetCourrier
				+ ", detailsCourrier=" + detailsCourrier
				+ ", dateCreationCourrier=" + dateCreationCourrier
				+ ", nature=" + nature + ", langue=" + langue
				+ ", destinataireUser="
				//+ destinataireUser + ", emetteurUnite=" + emetteurUnite
				//+ ", destinataireUnite=" + destinataireUnite
				//+ ", emetteurContact=" + emetteurContact
				//+ ", destinataireContact=" + destinataireContact 
				+ "]";
	}


	public String getDateModification() {
		return dateModification;
	}


	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}


	@ManyToOne
	@JoinColumn(name = "idActor")
	public User getActor() {
		return actor;
	}


	public void setActor(User actor) {
		this.actor = actor;
	}

	
	
}
