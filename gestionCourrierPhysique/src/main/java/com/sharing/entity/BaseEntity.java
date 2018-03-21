package com.sharing.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	private String dateFormat;

    public String getDateFormat(Date originalDate){
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	String newDate="";
    	if(originalDate==null){
    		newDate= "";
    	}
    	else{
    		try {
    			newDate= formatter.format(originalDate);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
		return newDate;
	}
    
    public String getBigDateFormat(Date originalDate){
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	String newDate="";
    	if(originalDate==null){
    		newDate= "Pas de réception";
    	}
    	else{
    		try {
    			newDate= formatter.format(originalDate);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
		return newDate;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

}