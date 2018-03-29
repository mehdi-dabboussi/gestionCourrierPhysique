package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Langue
 *
 */
@Entity

public class Langue implements Serializable {

	
	private Long idLangue;
	private String libelleLangue;
	private static final long serialVersionUID = 1L;

	public Langue() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdLangue() {
		return this.idLangue;
	}

	public void setIdLangue(Long idLangue) {
		this.idLangue = idLangue;
	}   
	public String getLibelleLangue() {
		return this.libelleLangue;
	}

	public void setLibelleLangue(String libelleLangue) {
		this.libelleLangue = libelleLangue;
	}
   
}
