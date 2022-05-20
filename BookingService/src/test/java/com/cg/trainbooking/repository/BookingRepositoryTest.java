package com.cg.trainbooking.repository;

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

import com.cg.trainbooking.model.Booking;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookingRepositoryTest {

	//Object of TrainDetailRepositoryTest to communicate with Repository
	@Autowired
	private BookingRepository repository;
	
	
	private Booking book1;
    private Booking book2;
    List<Booking> BookingList;
    LocalDate myDate1;
    
    @BeforeEach
    public void setUp() {
    BookingList=new ArrayList<>(); 
    myDate1 =LocalDate.parse("2022-05-14");
    book1= new Booking(99,"Chennai Express",25,100,2,myDate1); //user input
    book2= new Booking(89,"Mumbai Express",45,200,4,myDate1);  //user input 
    BookingList.add(book1);
    BookingList.add(book2);
    }
    
    @AfterEach
    public void tearDown() {
    	book1 = book2 = null;
    	BookingList = null;
    }
	
	
    LocalDate myDate;
	@Test
	public void givenBookingShouldReturnBookingObject() {
		repository.save(book1);  // Data is saved in Database
		Booking p2= repository.findById(book1.getPnr()).get(); //Fetching the data from DataBase
		assertNotNull(p2);  //To check if it returning the Passenger Object
		assertEquals(book1.getTrain_name(),p2.getTrain_name());		
	}
	
	@Test
	public void getAllMustReturnAllBooking() {
		repository.save(book1);                             // Data is saved in Database
		repository.save(book2);                             // Data is saved in Database
		List<Booking> passLis=(List<Booking>)repository.findAll(); 
		assertEquals("Chennai Express",passLis.get(0).getTrain_name());
	}
	

	//Test Case for deleteTrainDetailBypid Implementation
	@Test
	public void cancelBooking()  {
		repository.save(book1);                             // Data is saved in Database
		repository.deleteById(book1.getTrain_no());
		Optional<Booking> optional = repository.findById(book1.getTrain_no());
	     assertEquals(Optional.empty(), optional);
		}
	
	//Test Case for getByIdTrainDetail Implementation
	@Test
	public void givenIdThenShouldReturnTrainOfThatId() {
		repository.save(book2);
	     Optional<Booking> optional =  repository.findById(book2.getPnr());
	     assertEquals(book2.getPnr(), optional.get().getPnr());
	     assertEquals(book2.getTrain_name(), optional.get().getTrain_name());
	}
	
	//updateTrainDetail Implementation
	@Test
	public void updateTrainDetailById()  {
		 repository.save(book1);
	     String name2="Pune";
	     book1= new Booking(99,name2,25,100,2,myDate1); //user input
	     repository.save(book1);
	     assertEquals(book1.getTrain_name(), name2);
	     
	}

}