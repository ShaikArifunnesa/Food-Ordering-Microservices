package com.online.menufoodservice.exception;

public class ItemExistException extends RuntimeException {
	
private static final long serialVersionUID=1L;
	
	public ItemExistException(String message) {
		super(message);
	}


}
