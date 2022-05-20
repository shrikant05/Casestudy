package com.cg.trainbooking.exception;

public class PnrAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//default constructor
	public PnrAlreadyExistsException() {
		super();
		
	}

	//parameterized constructor
	public PnrAlreadyExistsException(String message) {
		super(message);
	
	}
	
	

}
