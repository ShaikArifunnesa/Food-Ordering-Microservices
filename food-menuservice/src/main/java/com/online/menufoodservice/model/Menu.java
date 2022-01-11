package com.online.menufoodservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {
	
  
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Integer itemId;
  // @NotEmpty(message="ItemName Can't be Empty")
   private String itemName;
   
  // @NotNull(message="Item price can't be null")
   private double itemPrice;
       
	public Integer getItemId() {
	return itemId;
	}
	public void setItemId(Integer itemId) {
	this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
    
    
}
