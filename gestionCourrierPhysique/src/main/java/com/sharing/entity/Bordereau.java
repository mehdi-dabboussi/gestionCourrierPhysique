package com.sharing.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bordereau
 *
 */
@Entity

public class Bordereau implements Serializable {

	private List<Transfert> transferts;
	private long idBordereau;
	private String dateCreation;
	private TransporteurExterne transporteurExterne;
	private static final long serialVersionUID = 1L;

	public Bordereau() {
		super();
	}   
	@Id    
	public long getIdBordereau() {
		return this.idBordereau;
	}

	public void setIdBordereau(long idBordereau) {
		this.idBordereau = idBordereau;
	}   
	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	@OneToMany(mappedBy = "bordereau",fetch=FetchType.EAGER)
	public List<Transfert> getTransferts() {
		return transferts;
	}
	public void setTransferts(List<Transfert> transferts) {
		this.transferts = transferts;
	}
	
	@ManyToOne
	@JoinColumn(name = "idTransporteurExterne")
	public TransporteurExterne getTransporteurExterne() {
		return transporteurExterne;
	}
	public void setTransporteurExterne(TransporteurExterne transporteurExterne) {
		this.transporteurExterne = transporteurExterne;
	}
	
	
   
}
