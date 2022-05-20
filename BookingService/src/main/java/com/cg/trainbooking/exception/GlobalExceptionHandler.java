package com.cg.trainbooking.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/*@ControllerAdvice is a specialization of the @Component annotation which allows 
to handle exceptions across the whole application in one global handling component.*/
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Value(value="${data.exception.msg1}")
	private String msg1;
	
	@Value(value="${data.exception.msg2}")
	private String msg2;
	
	
	@ExceptionHandler(value = PnrAlreadyExistsException.class)
	public ResponseEntity<String> PnrNoAlreadyExistsException(PnrAlreadyExistsException passException){
	        return new ResponseEntity<String>(msg1, HttpStatus.CONFLICT);
	   }
	@ExceptionHandler(value = PnrNotFound.class)
	public ResponseEntity<String> PnrNoNotFound(PnrNotFound pidException){
	        return new ResponseEntity<String>(msg2, HttpStatus.NOT_FOUND);
	   }
	
}
