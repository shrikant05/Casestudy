package com.cg.trainbooking.service;

import java.util.List;
import java.util.Optional;

import com.cg.trainbooking.common.TxResponse;
import com.cg.trainbooking.exception.PnrAlreadyExistsException;
import com.cg.trainbooking.exception.PnrNotFound;
import com.cg.trainbooking.model.Booking;

public interface BookingService {
	 public TxResponse placeBooking(Booking book) throws PnrAlreadyExistsException;
	 public Booking updateBookingDetail(Booking pass) throws PnrNotFound;
	 public Optional<Booking> getBookingDetailByPnr(int pid) throws PnrNotFound;; 
	 public List<Booking> getAllBookingDetail();
	 public Booking addBookingWithoutPayment(Booking book) throws PnrAlreadyExistsException;
	 public TxResponse cancelBookingBypnr(int pnr, Booking book) throws PnrNotFound;
}
