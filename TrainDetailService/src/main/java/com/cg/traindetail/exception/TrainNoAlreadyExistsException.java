package com.cg.traindetail.exception;

public class TrainNoAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//default constructor
	public TrainNoAlreadyExistsException() {
		super();
		
	}

	//parameterized constructor
	public TrainNoAlreadyExistsException(String message) {
		super(message);
	
	}
	
	

}
