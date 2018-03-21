package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UniteBancaire
 *
 */
@Entity
public class UniteBancaire implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idUniteBancaire;
	private String nomUniteBancaire;
	private String adresseUniteBancaire;
	private String telUniteBancaire;
	private String emailUniteBancaire;
	private String faxUniteBancaire;
	private List<User> users;
	

	public UniteBancaire() {
		super();
	}   
	
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdUniteBancaire() {
		return this.idUniteBancaire;
	}

	public void setIdUniteBancaire(Long idUniteBancaire) {
		this.idUniteBancaire = idUniteBancaire;
	}   
	
	@Column(name = "nomUniteBancaire")
	public String getNomUniteBancaire() {
		return this.nomUniteBancaire;
	}

	public void setNomUniteBancaire(String nomUniteBancaire) {
		this.nomUniteBancaire = nomUniteBancaire;
	}   
	
	public String getAdresseUniteBancaire() {
		return this.adresseUniteBancaire;
	}

	public void setAdresseUniteBancaire(String adresseUniteBancaire) {
		this.adresseUniteBancaire = adresseUniteBancaire;
	}   
	
	public String getTelUniteBancaire() {
		return this.telUniteBancaire;
	}

	public void setTelUniteBancaire(String telUniteBancaire) {
		this.telUniteBancaire = telUniteBancaire;
	}   
	
	public String getEmailUniteBancaire() {
		return this.emailUniteBancaire;
	}

	public void setEmailUniteBancaire(String emailUniteBancaire) {
		this.emailUniteBancaire = emailUniteBancaire;
	}   
	
	public String getFaxUniteBancaire() {
		return this.faxUniteBancaire;
	}

	public void setFaxUniteBancaire(String faxUniteBancaire) {
		this.faxUniteBancaire = faxUniteBancaire;
	}
	
	@OneToMany(mappedBy = "uniteBancaire",fetch=FetchType.EAGER)
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
   
}
