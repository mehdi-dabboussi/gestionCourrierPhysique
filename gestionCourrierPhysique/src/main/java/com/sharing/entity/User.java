package com.sharing.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: User
 *
 */

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long idUser;
	private String login;
	private String password;
	private String userName;
	private String surName;
	private boolean enabled;
	private String userEmail;

	private String historiqueIdentifier;
	private List<Role> roles;
	private UniteBancaire uniteBancaire;

	// ******** getters and setters ************//

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", login=" + login + ", password="
				+ password + ", userName=" + userName + ", surName=" + surName
				+ ", enabled=" + enabled + ", userEmail=" + userEmail
				+ ", historiqueIdentifier=" + historiqueIdentifier
				+ ", roles=" + roles + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", inverseJoinColumns = @JoinColumn(name = "idRole"), joinColumns = @JoinColumn(name = "idUser"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHistoriqueIdentifier() {
		return historiqueIdentifier;
	}
	public void setHistoriqueIdentifier(String historiqueIdentifier) {
		this.historiqueIdentifier = historiqueIdentifier;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@ManyToOne
	@JoinColumn(name = "idUniteBancaire")
	public UniteBancaire getUniteBancaire() {
		return uniteBancaire;
	}

	public void setUniteBancaire(UniteBancaire uniteBancaire) {
		this.uniteBancaire = uniteBancaire;
	}

}
