package com.sharing.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Nature
 *
 */
@Entity

public class Nature implements Serializable {

	
	private Long idNature;
	private String libelleNature;
	private static final long serialVersionUID = 1L;

	public Nature() {
		super();
	}   
	
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdNature() {
		return this.idNature;
	}

	public void setIdNature(Long idNature) {
		this.idNature = idNature;
	}   
	public String getLibelleNature() {
		return this.libelleNature;
	}

	public void setLibelleNature(String libelleNature) {
		this.libelleNature = libelleNature;
	}
   
}
