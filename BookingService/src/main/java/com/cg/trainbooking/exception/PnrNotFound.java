package com.cg.trainbooking.exception;

public class PnrNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//default constructor
	public PnrNotFound() {
		super();
		
	}
	
	//parameterized constructor
	public PnrNotFound(String message) {
		super(message);
		
	}
	@Override
	public String getMessage() {
		return "(PNR Number Not Found)";
	}
	

}
