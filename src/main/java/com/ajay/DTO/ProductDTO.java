package com.ajay.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private int productid;
	private String productname;
	private String description;
	private double price;
	private double weight;
	private int stockavailable;
	private String imageuri;
	private int categoryid;
}
