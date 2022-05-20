package com.cg.payment.exception;

public class TransactionIDAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//default constructor
	public TransactionIDAlreadyExistsException() {
		super();
		
	}

	//parameterized constructor
	public TransactionIDAlreadyExistsException(String message) {
		super(message);
	
	}
	
	

}
