package com.cg.traindetail.service;


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

import com.cg.traindetail.exception.TrainNoAlreadyExistsException;
import com.cg.traindetail.model.TrainDetail;
import com.cg.traindetail.repository.TrainDetailRepository;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TrainDetailServiceImplTest {

	/*
	 * by using @Mock we  create a dummy object to check service provided by TrainDetailRepository
	 */
	@Mock
	private TrainDetailRepository repository;
	
	
	@InjectMocks
	private TrainDetailServiceImpl service;
	
	
    
    private TrainDetail train1;
    private TrainDetail train2;
    List<TrainDetail> TrainList;
    Map<String, Integer> seats = new HashMap<String, Integer>(); 
    LocalDate myDate;
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
	
   // Test Case for Saving a Train Detail
	@Test
	public void TestAddTrain() throws TrainNoAlreadyExistsException{
		//stubbing mock to return specific data
		when(repository.save(any())).thenReturn(train1);
		service.addTrainDetail(train1);
		verify(repository,times(1)).save(any());
	}
	 
   

	//Test Code for Retrieval of all Train Details
	@Test 
	public void TestGetAllTrainDetail() {

	 
	  repository.save(train1); // Data is saved in Database
	  repository.save(train2); // Data is saved in Database
	  
	//stubbing mock to return specific data
	  when(repository.findAll()).thenReturn(TrainList); 
	  List<TrainDetail> passList1=service.getAllTrainDetail();
	  
	  assertEquals(passList1,TrainList); 
	  verify(repository,times(1)).save(train1);
	  verify(repository,times(1)).findAll(); }
	

	/*
	 * //Test Case to Retrieve a Train by Train Number
	 * 
	 * @Test public void getTrainDetailByNo() throws TrainNoNotFound {
	 * repository.save(train1);
	 * Mockito.when(repository.findById(1)).thenReturn(Optional.ofNullable(train1));
	 * Optional<TrainDetail> train=service.getTrainDetailById(train1.getTrain_no());
	 * assertEquals(train,train1); verify(repository,times(1)).findById(1);
	 * 
	 * 
	 * }
	 */
	

	 
	
}

