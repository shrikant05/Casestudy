package com.cg.payment.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.payment.exception.TransactionIDAlreadyExistsException;
import com.cg.payment.exception.TransactionIDNotFound;
import com.cg.payment.model.Payment;
import com.cg.payment.service.PaymentServiceImpl;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
	
	@Autowired
    PaymentServiceImpl service;

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Payment Service ] !";
    }
   
    @PostMapping("/pay")
    public Payment doPayment(@RequestBody Payment payment) throws TransactionIDAlreadyExistsException{
        return service.doPay(payment);
    }
    
	//Get All Records Of Train
	@GetMapping("/getAllPayment")
	public ResponseEntity<List<Payment>> getAllBooking() {
		//log.info("Get All Records Of Passenger from getAllPassenger()");
		return new ResponseEntity<List<Payment>>((List<Payment>)service.getAllPaymentDetail(),HttpStatus.OK);
				
	}
	 //Get Record Of Train by Train no.
	 @GetMapping("/getPaymentByTxn/{id}") 
	  public Optional<Payment> getPaymentDetailById(@PathVariable("id") String id) throws TransactionIDNotFound{
		 return service.getPaymentDetailById(id);
	   }
	
	
    // Update Train record
	 @PostMapping("/refundPayment")
	 public Payment refundPayment(@RequestBody Payment payment) throws TransactionIDAlreadyExistsException{
	        return service.refund(payment);
	    }
    

}



    