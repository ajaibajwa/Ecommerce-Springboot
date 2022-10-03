package com.ajay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendRegisterEmail(String useremail, String firstname, String businessEmail) {
		SimpleMailMessage userMsg = new SimpleMailMessage();
		SimpleMailMessage businessMsg = new SimpleMailMessage();
		
		userMsg.setTo(useremail);
		userMsg.setSubject("Ecommerce - Succesfully Registered.");
		userMsg.setText("Hello "+firstname +", \nYou have successfully registered with Ecommerce. ");
		
		javaMailSender.send(userMsg);
		
		businessMsg.setTo(businessEmail);
		businessMsg.setSubject("Ecommerce - New User '"+firstname+"' Registered");
		businessMsg.setText("Hello, \nNew user '"+firstname+"' registered successfully.");
		
		javaMailSender.send(businessMsg);
	}
	
	public void sendCheckoutEmail(String useremail, String firstname, String businessEmail, String orderDetails) {
		SimpleMailMessage userMsg = new SimpleMailMessage();
		SimpleMailMessage businessMsg = new SimpleMailMessage();
		
		userMsg.setTo(useremail);
		userMsg.setSubject("Ecommerce - Order Placed.");
		userMsg.setText("Hello "+firstname+", \nYour order has been placed succesffuly.\n".concat(orderDetails));
		
		javaMailSender.send(userMsg);
		
		businessMsg.setTo(businessEmail);
		businessMsg.setSubject("Ecommerce - New order.");
		businessMsg.setText("Hello, \n'"+firstname+"' placed a new order.\n".concat(orderDetails));
		
		javaMailSender.send(businessMsg);
	}

}
