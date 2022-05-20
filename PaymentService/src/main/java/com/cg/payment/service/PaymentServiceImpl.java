package com.cg.payment.service;

import com.cg.payment.exception.TransactionIDAlreadyExistsException;
import com.cg.payment.exception.TransactionIDNotFound;
import com.cg.payment.model.Payment;
import com.cg.payment.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository repository;
    
    //Do Payment  Implementation
    @Override
    public Payment doPay(Payment payment) throws TransactionIDAlreadyExistsException{
    	
		payment.setTxId(UUID.randomUUID().toString());
		payment.setPayment_type("paid");
		payment.setPaymentDate(LocalDate.now());
		return repository.save(payment); 
	}
    
    //Refund Payment  Implementation
    @Override
    public Payment refund(Payment payment) throws TransactionIDAlreadyExistsException{
		payment.setTxId(UUID.randomUUID().toString());
		payment.setPayment_type("refund");
		payment.setPaymentDate(LocalDate.now());
		return repository.save(payment); 
	}

	 //getAllPaymentDetail Implementation
	 @Override
	 public List<Payment> getAllPaymentDetail() {
		return (List<Payment>) repository.findAll();
	}

	 //getPaymentDetailById Implementation
	 @Override
	 public Optional<Payment> getPaymentDetailById(String pid) throws TransactionIDNotFound{
		 if(repository.existsById(pid)) {
				return repository.findById(pid);
			}
			else {
				throw new TransactionIDNotFound();
			}
	 }	
}