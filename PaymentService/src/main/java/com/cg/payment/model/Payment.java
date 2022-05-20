package com.cg.payment.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="payment")
public class Payment {
		
	    //attributes
		@Id
		private String txId; //TRansaction ID
		private int pnr;
	    private double totalAmount;
	    private LocalDate paymentDate;
	    private String payment_type; //paid,refund
	    
	    //default constructor
	    public Payment() {
			super();
		}
	    
	    //contructor with feilds
		public Payment(String txId, int pnr, double totalAmount, LocalDate paymentDate,String payment_type) {
			super();
			this.txId = txId;
			this.pnr = pnr;
			this.totalAmount = totalAmount;
			this.paymentDate = paymentDate;
			this.payment_type=payment_type;
		}

		//getter and setter
		public String getTxId() {
			return txId;
		}


		public void setTxId(String txId) {
			this.txId = txId;
		}


		public int getPnr() {
			return pnr;
		}


		public void setPnr(int pnr) {
			this.pnr = pnr;
		}


		public double getTotalAmount() {
			return totalAmount;
		}


		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}


		public LocalDate getPaymentDate() {
			return paymentDate;
		}


		public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}


		public String getPayment_type() {
			return payment_type;
		}


		public void setPayment_type(String payment_type) {
			this.payment_type = payment_type;
		}
}