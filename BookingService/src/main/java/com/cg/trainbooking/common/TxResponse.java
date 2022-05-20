package com.cg.trainbooking.common;

import java.time.LocalDate;


import com.cg.trainbooking.model.Booking;

public class TxResponse {
		//attributes
	    private Booking book;
	    private double amount;
	    private String txId;
	    private String payment_type; //paid,refund
	    private LocalDate paymentDate;
	    
	    //default constructor
	    public LocalDate getPaymentDate() {
			return paymentDate;
		}

	    //constructor with fields 
	    public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}
	    
	    //getter and setter
	    public Booking getOrder() {
	        return book;
	    }

	    public void setOrder(Booking book) {
	        this.book = book;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public String getTxId() {
	        return txId;
	    }

	    public void setTxId(String txId) {
	        this.txId = txId;
	    }


		public String getPayment_type() {
			return payment_type;
		}


		public void setPayment_type(String payment_type) {
			this.payment_type = payment_type;
		}

	   
}
