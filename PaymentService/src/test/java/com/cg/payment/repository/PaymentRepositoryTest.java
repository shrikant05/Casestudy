package com.cg.payment.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.payment.model.Payment;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class PaymentRepositoryTest {

	//Object of PaymentRepositoryTest to communicate with Repository
	@Autowired
	private PaymentRepository repository;
	
	
	private Payment pay1;
    private Payment pay2;
    List<Payment> PaymentList;
    LocalDate myDate1;
    
    @BeforeEach
    public void setUp() {
    PaymentList=new ArrayList<>(); 
    myDate1 =LocalDate.parse("2022-05-14");
    pay1= new Payment("17894",456,100,myDate1,"paid"); //user input
    pay2= new Payment("24564",789,150,myDate1,"refund");  //user input 
    PaymentList.add(pay1);
    PaymentList.add(pay2);
    }
    
    @AfterEach
    public void tearDown() {
    	pay2 = null;
    	pay1=null;
    	PaymentList = null;
    }
	 
	@Test
	public void givenPaymentShouldReturnpaymentObject() {
		repository.save(pay1);  // Data is saved in Database
		Payment p2= repository.findById(pay1.getTxId()).get(); //Fetching the data from DataBase
		assertNotNull(p2);  //To check if it returning the Passenger Object
		assertEquals(pay1.getPayment_type(),p2.getPayment_type());		
	}
	
	@Test
	public void getAllMustReturnAllPayment() {
		repository.save(pay1);                             // Data is saved in Database
		repository.save(pay2);                             // Data is saved in Database
		List<Payment> passLis=(List<Payment>)repository.findAll(); 
		assertEquals(0,passLis.get(1).getPnr());
	}
	
	
	//Test Case for getByPaymentDetail Implementation
	@Test
	public void givenIdThenShouldReturnTrainOfThatId() {
	     Optional<Payment> optional =  repository.findById(pay2.getTxId());
	     assertEquals(pay2.getPnr(), optional.get().getPnr());
	     assertEquals(pay2.getTotalAmount(), optional.get().getTotalAmount());
	}
}
