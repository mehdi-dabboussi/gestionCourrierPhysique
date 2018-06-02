package com.sharing.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: UniteBancaire
 *
 */
@Entity
public class UniteBancaire extends Emetteur_Recepteur implements Serializable {

	
	//private Long idUniteBancaire;

	//private List<User> users;
	private static final long serialVersionUID = 1L;

	public UniteBancaire() {
		super();
	}   
	
	/*@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdUniteBancaire() {
		return this.idUniteBancaire;
	}

	public void setIdUniteBancaire(Long idUniteBancaire) {
		this.idUniteBancaire = idUniteBancaire;
	}   */
	
	
	/*@OneToMany(mappedBy = "uniteBancaire",fetch=FetchType.EAGER)
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}*/
   
}
