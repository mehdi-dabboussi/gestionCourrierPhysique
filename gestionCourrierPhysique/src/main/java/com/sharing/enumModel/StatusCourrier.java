package com.sharing.enumModel;

public enum StatusCourrier {
	blocked("blocked"),
	inProgress("inProgress"),
	completed("completed"),
	deleted("deleted");
	
	 private String status = "";
	
	StatusCourrier(String status) {
		 this.status = status;
	}
	
	public String toString(){
	    return status;
	  }

}
