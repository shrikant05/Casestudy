package com.cg.trainbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.trainbooking.common.Payment;
import com.cg.trainbooking.common.TxResponse;
import com.cg.trainbooking.exception.PnrAlreadyExistsException;
import com.cg.trainbooking.exception.PnrNotFound;
import com.cg.trainbooking.model.Booking;
import com.cg.trainbooking.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
    private BookingRepository repository;

    @Autowired
    private RestTemplate template;
    
    //placeBookingWithPayment Implementation  
    @Override
    public TxResponse placeBooking(Booking book) throws PnrAlreadyExistsException{
        if(repository.existsById(book.getPnr())) {
			throw new PnrAlreadyExistsException();
		}else{
        repository.save(book);
        Payment paymentReq = new Payment();
        paymentReq.setPnr(book.getPnr());
        paymentReq.setTotalAmount(book.getPrice_per_seat() * book.getSeat_count());
        Payment paymentRes =template.postForObject("http://localhost:8084/api/v1/payment/pay",paymentReq, Payment.class);
        TxResponse txResponse = new TxResponse();
        txResponse.setOrder(book);
        txResponse.setPayment_type(paymentRes.getPayment_type());
        txResponse.setPaymentDate(paymentRes.getPaymentDate());
        txResponse.setAmount(paymentRes.getTotalAmount());
        txResponse.setTxId(paymentRes.getTxId());
        return txResponse;
        }}
      
    //addBookingWithoutPayment Implementation  
	@Override 
	public Booking addBookingWithoutPayment(Booking book) throws PnrAlreadyExistsException{
		  if(repository.existsById(book.getPnr())) {
			throw new PnrAlreadyExistsException();
			
		  }else {
			  Booking saveBooking=repository.save(book); 
			  return saveBooking; 
		  }
	  }

	//update Booking Implementation
	@Override
	public Booking updateBookingDetail(Booking book) throws PnrNotFound {
		Booking updateBooking=repository.save(book);
		return updateBooking;
	}

	//getBookingDetailByPnr  Implementation
	@Override
	public Optional<Booking> getBookingDetailByPnr(int pnr) throws PnrNotFound {
		if(repository.existsById(pnr)) {
			return repository.findById(pnr);
		}
		else {
			throw new PnrNotFound();
		}
	}

	//getAllBookingDetail Implementation
	@Override
	public List<Booking> getAllBookingDetail() {
		return (List<Booking>) repository.findAll();
	}

	//cancelBookingBypnr  Implementation
	@Override
	public TxResponse cancelBookingBypnr(int pnr,Booking book) throws PnrNotFound {
		if(repository.existsById(pnr)) {
			Payment paymentReq = new Payment();
	        paymentReq.setPnr(pnr);
	        double a=repository.findById(pnr).get().getPrice_per_seat();
	        int b=repository.findById(pnr).get().getSeat_count();
	        paymentReq.setTotalAmount(a*b);
	        Payment paymentRes =template.postForObject("http://localhost:8084/api/v1/payment/refundPayment",paymentReq, Payment.class);
	        TxResponse txResponse = new TxResponse();
	        txResponse.setOrder(book);
	        txResponse.setPayment_type(paymentRes.getPayment_type());
	        txResponse.setPaymentDate(paymentRes.getPaymentDate());
	        txResponse.setAmount(paymentRes.getTotalAmount());
	        txResponse.setTxId(paymentRes.getTxId());
	        repository.deleteById(pnr);
	        return txResponse;
	        }
		else {
			throw new  PnrNotFound();
		}
		}
	  
}
