package com.cg.payment.service;

import java.util.List;
import java.util.Optional;

import com.cg.payment.exception.TransactionIDAlreadyExistsException;
import com.cg.payment.exception.TransactionIDNotFound;
import com.cg.payment.model.Payment;

public interface PaymentService {
	
	public Payment doPay(Payment payment) throws TransactionIDAlreadyExistsException;
	public List<Payment> getAllPaymentDetail();
	Optional<Payment> getPaymentDetailById(String pid) throws TransactionIDNotFound;
	Payment refund(Payment payment) throws TransactionIDAlreadyExistsException;
	
	}
