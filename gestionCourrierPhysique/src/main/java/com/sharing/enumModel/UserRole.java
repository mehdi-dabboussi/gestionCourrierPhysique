package com.sharing.enumModel;

public enum UserRole {
	roleAdmin("ROLE_ADMIN"),
	roleUser("ROLE_USER"),
	roleResponsableBanque("ROLE_RESPONSABLE_COURRIER_BANQUE"),
	roleBureauOrdre("ROLE_BUREAU_ORDRE");
	
	 private String intituleRole = "";
	
	UserRole(String intituleRole) {
		 this.intituleRole = intituleRole;
	}
	
	public String toString(){
	    return intituleRole;
	  }

}
