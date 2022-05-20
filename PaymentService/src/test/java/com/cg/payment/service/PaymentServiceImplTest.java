package com.cg.payment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.payment.exception.TransactionIDAlreadyExistsException;
import com.cg.payment.model.Payment;
import com.cg.payment.repository.PaymentRepository;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
	/*
	 * by using @Mock we  create a dummy object to check service provided by TrainDetailRepository
	 */
	@Mock
	private PaymentRepository repository;
	
	
	@InjectMocks
	private PaymentServiceImpl service;
	
	
    
	private Payment pay1;
	private Payment pay2;
	private Payment pay4;
	
 
    List<Payment> PaymentList;
    Map<String, Integer> seats = new HashMap<String, Integer>(); 
    LocalDate myDate;
    @BeforeEach
    public void setUp() {
    PaymentList=new ArrayList<>(); 
    seats.put("D1", 45);
    myDate =LocalDate.parse("2022-05-14");
    pay1= new Payment("17894",456,100,myDate,"paid"); //user input
    pay4= new Payment("1789",456,100,myDate,"refund"); //user input
    pay2= new Payment("17894",456,100,myDate,"paid"); //user input
    PaymentList.add(pay1);
    PaymentList.add(pay2);
    }
    @AfterEach
    public void tearDown() {
    	pay1 = pay2 = null;
    	pay4=null;
    	PaymentList = null;
    }
	
   // Test Case for Saving a Payment Detail
	@Test
	public void TestAddPayment() throws TransactionIDAlreadyExistsException{
		//stubbing mock to return specific data
		when(repository.save(any())).thenReturn(pay1);
		service.doPay(pay1);
		verify(repository,times(1)).save(any());
	}
	 
	
	  // Test Case for Saving a Payment Detail
		@Test
		public void TestAddRefundPayment() throws TransactionIDAlreadyExistsException{
			//stubbing mock to return specific data
			when(repository.save(any())).thenReturn(pay4);
			service.refund(pay4);
			verify(repository,times(1)).save(any());
		}
   

	//Test Code for Retrieval of all Payment Details
	@Test 
	public void TestGetAllPaymentDetail() {

	 
	  repository.save(pay1); // Data is saved in Database
	  repository.save(pay2); // Data is saved in Database
	  
	//stubbing mock to return specific data
	  when(repository.findAll()).thenReturn(PaymentList); 
	  List<Payment> passList1=service.getAllPaymentDetail();
	  
	  assertEquals(passList1,PaymentList); 
	  verify(repository,times(1)).save(pay1);
	  verify(repository,times(1)).findAll(); }
	

	

	

	
}