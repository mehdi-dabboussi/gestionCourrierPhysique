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

public class ContactExterne extends Emetteur_Recepteur implements Serializable {

	
	//private Long idContactExterne;
	//private List<SousContactExterne> sousContactExternes;
	private static final long serialVersionUID = 1L;

	public ContactExterne() {
		super();
	}   
	/*@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdContactExterne() {
		return this.idContactExterne;
	}

	public void setIdContactExterne(Long idContactExterne) {
		this.idContactExterne = idContactExterne;
	}   */
	
	
	/*@OneToMany(mappedBy = "contactExterne",fetch=FetchType.EAGER)
	public List<SousContactExterne> getSousContactExternes() {
		return this.sousContactExternes;
	}

	public void setSousContactExternes(List<SousContactExterne> sousContactExternes) {
		this.sousContactExternes = sousContactExternes;
	}*/
   
}
