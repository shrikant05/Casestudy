package com.cg.trainbooking.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.trainbooking.exception.PnrAlreadyExistsException;
import com.cg.trainbooking.model.Booking;
import com.cg.trainbooking.repository.BookingRepository;




@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {
	/*
	 * by using @Mock we  create a dummy object to check service provided by BookingRepository
	 */
	@Mock
	private BookingRepository repository;
	
	@InjectMocks
	private BookingServiceImpl service;
	
	
    
    private Booking book1;
    private Booking book2;
    List<Booking> BookingList;
    LocalDate myDate;
    
    @BeforeEach
    public void setUp() {
    BookingList=new ArrayList<>(); 
    myDate =LocalDate.parse("2022-05-14");
    book1= new Booking(99,"Chennai Express",25,100,2,myDate); //user input
    book2= new Booking(89,"Mumbai Express",45,200,4,myDate);  //user input 
    BookingList.add(book1);
    BookingList.add(book2);
    }
    
    @AfterEach
    public void tearDown() {
    	book1 = book2 = null;
    	BookingList = null;
    }
	
   // Test Case for Saving a Booking Detail
	@Test
	public void TestAddBooking() throws PnrAlreadyExistsException{
		//stubbing mock to return specific data
		when(repository.save(any())).thenReturn(book1);
		service.addBookingWithoutPayment(book1);
		verify(repository,times(1)).save(any());
	}
	
   

	
	  //Test Code for Retrieval of all Booking Details
	  
	  @Test public void TestGetAllBookingDetail() {
	  
	  
	  repository.save(book1); // Data is saved in Database 
	  repository.save(book2);// Data is saved in Database
	  
	  //stubbing mock to return specific data
	  when(repository.findAll()).thenReturn(BookingList); List<Booking>
	  passList1=service.getAllBookingDetail();
	  
	  assertEquals(passList1,BookingList); verify(repository,times(1)).save(book1);
	  verify(repository,times(1)).findAll(); }
	 
}