package com.cg.traindetail.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.traindetail.model.TrainDetail;
import com.cg.traindetail.service.TrainDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;





@ExtendWith(MockitoExtension.class)
class TrainDetailControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private TrainDetailService passServ;
	private TrainDetail train1;
	private Optional<TrainDetail> train2;
	List<TrainDetail> TrainList;
	LocalDate myDate;
	Map<String, Integer> seats = new HashMap<String, Integer>(); 
	
	@InjectMocks
	private TrainDetailController passController;

	
	
	@BeforeEach
	public void setup() {
		
		seats.put("D1", 45);
	    myDate =LocalDate.parse("2022-05-14");
		train1= new TrainDetail(1,"Chennai Express",seats,null,"Chennai","Mumbai"); //user input
		train2= Optional.of(new TrainDetail(1,"Chennai Express",seats,null,"Chennai","Mumbai")); //user input
		mockMvc=MockMvcBuilders.standaloneSetup(passController).build();
	}
	
	
	@Test
	public void addPassengerControllerTest() throws Exception{
		when(passServ.addTrainDetail(any())).thenReturn(train1);
		mockMvc.perform(post("/api/v1/traindetail/addTrain")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(train1)))
				.andExpect(status().isCreated());
		verify(passServ,times(1)).addTrainDetail(any());
	}
	
	
	@Test
	public void getAllPassenger() throws Exception {
		when(passServ.getAllTrainDetail()).thenReturn(TrainList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/traindetail/getAllTrain")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(train1)))
				.andDo(MockMvcResultHandlers.print());
		verify(passServ,times(1)).getAllTrainDetail();
		
	}
	
	@Test
	public void GetMappingOfTrainShouldReturnRespectiveTrain() throws Exception {
	    when(passServ.getTrainDetailById(train1.getTrain_no())).thenReturn(train2);
	    mockMvc.perform(get("/api/v1/traindetail/getTrainById/1").
	           contentType(MediaType.APPLICATION_JSON).
	           content(asJSONString(train1))).
	           andExpect(MockMvcResultMatchers.status().isOk()).
	           andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteMappingOfTrainShouldDeleteRespectiveTrain() throws Exception {
		 String uri = "/api/v1/traindetail/deleteTrainById/1";
		   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(204, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   assertEquals(content, "");
	}
	

	private static String asJSONString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	 
}