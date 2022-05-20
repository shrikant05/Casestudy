package com.cg.traindetail.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class TrainDetail {

	//attributes
	@Id
	private int train_no;
	private String train_name;
	Map<String, Integer> seats = new HashMap<String, Integer>(); 
	private LocalDate travelDate;
	private String startStation;
	private String destStation;
	
	//default constructor
	public TrainDetail() {
		super();
	}
	
	//constructor with all fields
	public TrainDetail(int train_no, String train_name, Map<String, Integer> seats, LocalDate travelDate,
			String startStation, String destStation) {
		super();
		this.train_no = train_no;
		this.train_name = train_name;
		this.seats = seats;
		this.travelDate = travelDate;
		this.startStation = startStation;
		this.destStation = destStation;
	}
	
	//getter and setter
	public int getTrain_no() {
		return train_no;
	}
	public void setTrain_no(int train_no) {
		this.train_no = train_no;
	}
	public String getTrain_name() {
		return train_name;
	}
	public void setTrain_name(String train_name) {
		this.train_name = train_name;
	}
	public Map<String, Integer> getSeats() {
		return seats;
	}
	public void setSeats(Map<String, Integer> seats) {
		this.seats = seats;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getDestStation() {
		return destStation;
	}
	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	//toString Method
	@Override
	public String toString() {
		return "TrainDetail [train_no=" + train_no + ", train_name=" + train_name + ", seats=" + seats + ", travelDate="
				+ travelDate + ", startStation=" + startStation + ", destStation=" + destStation + "]";
	}
	
	
	
	
}
