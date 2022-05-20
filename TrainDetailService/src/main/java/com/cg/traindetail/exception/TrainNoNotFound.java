package com.cg.traindetail.exception;

public class TrainNoNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//default constructor
	public TrainNoNotFound() {
		super();
		
	}
	
	//parameterized constructor
	public TrainNoNotFound(String message) {
		super(message);
		
	}
	@Override
	public String getMessage() {
		return "(Train Number Not Found)";
	}
	

}
