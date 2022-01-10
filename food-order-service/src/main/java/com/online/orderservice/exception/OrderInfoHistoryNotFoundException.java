package com.online.orderservice.exception;

public class OrderInfoHistoryNotFoundException extends RuntimeException
{
	
	private static final long serialVersionUID=1L;
	
	public OrderInfoHistoryNotFoundException(String message)
	{
		super(message);
	}

}