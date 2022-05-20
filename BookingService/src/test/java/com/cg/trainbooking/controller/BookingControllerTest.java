package com.cg.trainbooking.controller;

import static org.junit.jupiter.api.Assertions.*;
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

import com.cg.trainbooking.model.Booking;
import com.cg.trainbooking.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private BookingService service;
	private Booking book1;
	private Optional<Booking> book2;
	List<Booking> BookList;
	LocalDate myDate;
	Map<String, Integer> seats = new HashMap<String, Integer>(); 
	
	@InjectMocks
	private BookingController bookController;

	
	
	@BeforeEach
	public void setup() {
	    book1= new Booking(99,"Chennai Express",25,100,2,null); //user input
	    book2= Optional.of(new Booking(99,"Chennai Express",25,100,2,null)); //user input
		mockMvc=MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	//TestCase for AddBooking Controller
	@Test
	public void addBookingControllerTest() throws Exception{
		when(service.addBookingWithoutPayment(any())).thenReturn(book1);
		mockMvc.perform(post("/api/v1/booking/placeBooking")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(book1)))
				.andExpect(status().isCreated());
		verify(service,times(1)).addBookingWithoutPayment(any());
	}
	
	//TestCase for GetAllBooking Controller
	@Test
	public void getAllBooking() throws Exception {
		when(service.getAllBookingDetail()).thenReturn(BookList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/booking/getAllBooking")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(book1)))
				.andDo(MockMvcResultHandlers.print());
		verify(service,times(1)).getAllBookingDetail();
		
	}
	
	//TestCase for Get respective booking by pnr 
	@Test
	public void GetMappingOfBookingShouldReturnRespectiveBooking() throws Exception {
	    when(service.getBookingDetailByPnr(book1.getPnr())).thenReturn(book2);
	    mockMvc.perform(get("/api/v1/booking/getBookingByPnr/99").
	           contentType(MediaType.APPLICATION_JSON).
	           content(asJSONString(book1))).
	           andExpect(MockMvcResultMatchers.status().isOk()).
	           andDo(MockMvcResultHandlers.print());
	}

	//TestCase for Delete respective booking by pnr
	@Test
	public void deleteMappingOfBookingShouldDeleteRespectiveBooking() throws Exception {
		 String uri = "/api/v1/booking/cancelBookingByPnr/99";
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