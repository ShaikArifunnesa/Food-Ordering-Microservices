package com.online.menufoodservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MenuDTO {
	
	@NotEmpty(message="ItemName Can't be Empty")
	private String itemName;
	
	@NotNull(message="Item price can't be null")
	@Min(value=1)
	private double itemPrice;

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
