package com.cg.trainbooking.model;


import java.time.LocalDate;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="BookingDetails")
public class Booking {
	
	    //Attributes
	 	@Id 
	 	private int pnr;
		private String train_name;
		private int train_no;
		private double price_per_seat;
		private int seat_count;
		private LocalDate dateOfJourney;
		
		//default constructor
		public Booking() {
			super();
		}
		
		//constructor with fields
		public Booking(int pnr, String train_name, int train_no, double price_per_seat, int seat_count,
				LocalDate dateOfJourney) {
			super();
			this.pnr = pnr;
			this.train_name = train_name;
			this.train_no = train_no;
			this.price_per_seat = price_per_seat;
			this.seat_count = seat_count;
			this.dateOfJourney = dateOfJourney;
		}
		
		//getter and setter 
		public int getPnr() {
			return pnr;
		}
		
		public void setPnr(int pnr) {
			this.pnr = pnr;
		}
		
		public String getTrain_name() {
			return train_name;
		}
		
		public void setTrain_name(String train_name) {
			this.train_name = train_name;
		}
		
		public int getTrain_no() {
			return train_no;
		}
		
		public void setTrain_no(int train_no) {
			this.train_no = train_no;
		}
		
		public double getPrice_per_seat() {
			return price_per_seat;
		}
		
		public void setPrice_per_seat(double price_per_seat) {
			this.price_per_seat = price_per_seat;
		}
		
		public int getSeat_count() {
			return seat_count;
		}
		
		public void setSeat_count(int seat_count) {
			this.seat_count = seat_count;
		}
		
		public LocalDate getDateOfJourney() {
			return dateOfJourney;
		}
		
		public void setDateOfJourney(LocalDate dateOfJourney) {
			this.dateOfJourney = dateOfJourney;
		}
}
