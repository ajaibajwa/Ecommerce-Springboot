package com.ajay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressid;
	private String apartmentnumber;
	private String street;
	private String city;
	private String state;
	private String postalcode;
	private String country;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private User user;

	public Address(int addressid, String apartmentnumber, String street, String city, String state, String postalcode,
			String country) {
		this.addressid = addressid;
		this.apartmentnumber = apartmentnumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalcode = postalcode;
		this.country = country;
	}

}
