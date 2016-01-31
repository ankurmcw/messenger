package com.ankur.messenger.exceptions;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028968049928293627L;
	
	public DataNotFoundException(String message){
		super(message);
	}
}
