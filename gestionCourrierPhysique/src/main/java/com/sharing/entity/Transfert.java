package com.sharing.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Transfert
 *
 */
@Entity
public class Transfert implements Serializable {
	private long idTransfert;
	private String dateTransfert;
	private String estimation;
	private Courrier courrier;
	private User emetteurUser;
	private UniteBancaire emetteurUnite;
	private UniteBancaire destinataireUnite;
	private ContactExterne destinataireContact;
	private Bordereau bordereau;

	private String destinataireType;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdTransfert() {
		return idTransfert;
	}

	public void setIdTransfert(long idTransfert) {
		this.idTransfert = idTransfert;
	}

	public String getDateTransfert() {
		return dateTransfert;
	}

	public void setDateTransfert(String dateTransfert) {
		this.dateTransfert = dateTransfert;
	}

	public String getEstimation() {
		return estimation;
	}

	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}

	@ManyToOne
	@JoinColumn(name = "idCourrier")
	public Courrier getCourrier() {
		return courrier;
	}

	public void setCourrier(Courrier courrier) {
		this.courrier = courrier;
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
	@JoinColumn(name = "idUniteBancaireDestinataire")
	public UniteBancaire getDestinataireUnite() {
		return destinataireUnite;
	}

	public void setDestinataireUnite(UniteBancaire destinataireUnite) {
		this.destinataireUnite = destinataireUnite;
	}

	@ManyToOne
	@JoinColumn(name = "idContactExterneDestinataire")
	public ContactExterne getDestinataireContact() {
		return destinataireContact;
	}

	public void setDestinataireContact(ContactExterne destinataireContact) {
		this.destinataireContact = destinataireContact;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDestinataireType() {
		return destinataireType;
	}

	public void setDestinataireType(String destinataireType) {
		this.destinataireType = destinataireType;
	}

	@Override
	public String toString() {
		return "Transfert [idTransfert=" + idTransfert + ", dateTransfert="
				+ dateTransfert + ", estimation=" + estimation + ", courrier="
				+ courrier + ", emetteurUnite=" + emetteurUser
				+ ", destinataireUnite=" + destinataireUnite
				+ ", destinataireContact=" + destinataireContact + "]";
	}

	private static final long serialVersionUID = 1L;

	public Transfert() {
		super();
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
	@JoinColumn(name = "idBordereau")
	public Bordereau getBordereau() {
		return bordereau;
	}

	public void setBordereau(Bordereau bordereau) {
		this.bordereau = bordereau;
	}
	
	

}
