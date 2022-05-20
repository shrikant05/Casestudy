package com.cg.traindetail.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.traindetail.exception.TrainNoNotFound;
import com.cg.traindetail.model.TrainDetail;



@SpringBootTest
@ExtendWith(SpringExtension.class)
class TrainDetailRepositoryTest {

	//Object of TrainDetailRepositoryTest to communicate with Repository
	@Autowired
	private TrainDetailRepository repository;
	
	//Attributes
	Map<String, Integer> seats = new HashMap<String, Integer>(); 
    LocalDate myDate;
    List<TrainDetail> TrainList;
    private TrainDetail train1;
    private TrainDetail train2;
    
    @BeforeEach
    public void setUp() {
    TrainList=new ArrayList<>(); 
    seats.put("D1", 45);
    myDate =LocalDate.parse("2022-05-14");
    train1= new TrainDetail(1,"Chennai Express",seats,myDate,"Chennai","Mumbai"); //user input
    train2= new TrainDetail(2,"Mumbai Express",seats,myDate,"Mumbai","Pune");  //user input 
    TrainList.add(train1);
    TrainList.add(train2);
    }
    @AfterEach
    public void tearDown() {
    	train1 = train2 = null;
    	TrainList = null;
    }
    
    //Test Case for AddTrain
	@Test
	public void givenTrainShouldReturnTrainObject() {
		repository.save(train1);  // Data is saved in Database
		TrainDetail p2= repository.findById(train1.getTrain_no()).get(); //Fetching the data from DataBase
		assertNotNull(p2);  //To check if it returning the Passenger Object
		assertEquals(train1.getTrain_name(),p2.getTrain_name());		
	}
	
	//Test Case For getAllPassenger 
	@Test
	public void getAllMustReturnAllPassenger() {
		repository.save(train1);                             // Data is saved in Database
		repository.save(train2);                             // Data is saved in Database
		List<TrainDetail> passLis=(List<TrainDetail>)repository.findAll(); 
		assertEquals("Chennai Express",passLis.get(1).getTrain_name());
	}

	//Test Case for deleteTrainDetailBypid Implementation
	@Test
	public void deleteTrain() throws TrainNoNotFound {
		repository.save(train1);                             // Data is saved in Database
		repository.deleteById(train1.getTrain_no());
		Optional<TrainDetail> optional = repository.findById(train1.getTrain_no());
	     assertEquals(Optional.empty(), optional);
		}
	
	//Test Case for getByIdTrainDetail Implementation
	@Test
	public void givenIdThenShouldReturnTrainOfThatId() {
	     Optional<TrainDetail> optional =  repository.findById(train2.getTrain_no());
	     assertEquals(train2.getTrain_no(), optional.get().getTrain_no());
	     assertEquals(train2.getTrain_name(), optional.get().getTrain_name());
	}
	
	//updateTrainDetail Implementation
	@Test
	public void updateTrainDetailById() throws TrainNoNotFound {
		 repository.save(train1);
	     String name2="Pune";
	     train1= new TrainDetail(1,name2,seats,myDate,"Chennai","Mumbai"); //user input
	     repository.save(train1);
	     assertEquals(train1.getTrain_name(), name2);
	     
	}
}
