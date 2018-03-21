package com.websystique.spring.scheduling;

import java.util.Date;


public class MyBean {

	//@Scheduled(fixedRate=500)
	public void printMessage() {
		System.out.println("I am called by Spring scheduler "+new Date());
	}
}
