package com.cg.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.payment.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment,String> {

}