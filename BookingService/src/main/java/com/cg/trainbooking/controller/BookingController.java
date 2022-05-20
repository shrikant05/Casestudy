package com.cg.trainbooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trainbooking.common.TxResponse;
import com.cg.trainbooking.exception.PnrAlreadyExistsException;
import com.cg.trainbooking.exception.PnrNotFound;
import com.cg.trainbooking.model.Booking;
import com.cg.trainbooking.service.BookingService;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {
		
    @Autowired
    private BookingService service;
    
    //constructor
    public BookingController(BookingService service) {
		super();
		this.service = service;
	}

    //Book Ticket with payment
	@PostMapping("/placeBooking/Payment")
    public TxResponse placeBookingWithPayment(@RequestBody Booking book) throws PnrAlreadyExistsException{
        return service.placeBooking(book);
    }
    
	//Book Ticket without payment
	@PostMapping("/placeBooking")
	public ResponseEntity<Booking> saveBooking(@RequestBody Booking pass) throws PnrAlreadyExistsException{
		Booking savePass=service.addBookingWithoutPayment(pass);
		return new ResponseEntity<>(savePass,HttpStatus.CREATED);
	}
	
	//Get All Booking Of Train
	@GetMapping("/getAllBooking")
	public ResponseEntity<List<Booking>> getAllBooking() {
		//log.info("Get All Records Of Passenger from getAllPassenger()");
		return new ResponseEntity<List<Booking>>((List<Booking>)service.getAllBookingDetail(),HttpStatus.OK);
	}
	 
	//Get Record Of Booking by pnr.
	 @GetMapping("/getBookingByPnr/{pnr}") 
	 public Optional<Booking> getBookingDetailById(@PathVariable("pnr") int pnr) throws PnrNotFound{
		 return service.getBookingDetailByPnr(pnr);
	   }
	
	 //Cancel Booking By pnr.
	@DeleteMapping("/cancelBookingByPnr/{pnr}")
	public ResponseEntity<Booking> cancelBookingBypnr(@PathVariable int pnr, Booking book)  throws PnrNotFound
	{
	    service.cancelBookingBypnr(pnr,book);
		return ResponseEntity.noContent().build();
	}
	
    // Update Booking record 
	@PutMapping("/updateBooking/{pnr}")
	public ResponseEntity<Booking> updatePassenger(@RequestBody Booking book,@PathVariable("pnr") int pnr) throws PnrNotFound  { 
		  Optional<Booking> getPassengerById =service.getBookingDetailByPnr(pnr);
		  if(getPassengerById.isPresent()) {
			  Booking saveBooking=service.updateBookingDetail(book);
		      return new ResponseEntity<>(saveBooking,HttpStatus.OK);
		  }else
		  {
			 throw new PnrNotFound();
		  }
		
	  }
    
}