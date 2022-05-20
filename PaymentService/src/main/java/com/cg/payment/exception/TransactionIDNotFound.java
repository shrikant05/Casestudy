package com.cg.payment.exception;

public class TransactionIDNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//default constructor
	public TransactionIDNotFound() {
		super();
		
	}
	
	//parameterized constructor
	public TransactionIDNotFound(String message) {
		super(message);
		
	}
	@Override
	public String getMessage() {
		return "(PNR Number Not Found)";
	}
	

}
