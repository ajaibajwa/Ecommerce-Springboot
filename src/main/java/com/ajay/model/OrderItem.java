package com.ajay.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetails")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "userid") private User user;
	 */
	private int orderid;
	//private int userid;
	private String useremail;
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;
	private int quantity;

	
	public double getTotalPrice() {
		return product.getPrice()*quantity;
	}
}
