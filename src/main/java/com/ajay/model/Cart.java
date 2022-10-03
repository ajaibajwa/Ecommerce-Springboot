package com.ajay.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
@DynamicUpdate
public class Cart{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "userid") private User user;
	 */
	
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
