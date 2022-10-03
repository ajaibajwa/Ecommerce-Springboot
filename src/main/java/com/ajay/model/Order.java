package com.ajay.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;

	// private int userid;

	@Column(columnDefinition = "DECIMAL(10,2)")
	private double totalamount;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(columnDefinition = "DATE")
	private LocalDate orderdate;

	/*
	 * @ManyToOne(fetch = FetchType.EAGER, optional = false)
	 * 
	 * @JoinColumn(name = "userid", nullable = false) User user;
	 */

	private String useremail;
	// private String sessionid;
	private String status;
	private String shippingaddress;
	private String billingaddress;
	
	  @OneToMany
	  @JoinColumn(name = "orderid") 
	  List<OrderItem> orderItems;
	 
	  private double calculateTotalAmount() {
		  orderItems.forEach(c-> totalamount = c.getProduct().getPrice()*c.getQuantity());
		  return totalamount;
	  }
}
