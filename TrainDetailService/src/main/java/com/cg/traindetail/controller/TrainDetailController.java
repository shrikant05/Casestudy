package com.cg.traindetail.controller;

import java.util.List;
import java.util.Optional;

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

import com.cg.traindetail.exception.TrainNoAlreadyExistsException;
import com.cg.traindetail.exception.TrainNoNotFound;
import com.cg.traindetail.model.TrainDetail;
import com.cg.traindetail.service.TrainDetailService;

@RestController
@RequestMapping("/api/v1/traindetail")
public class TrainDetailController {

		 //Object of TrainDetailService to communicate with Service
		 private TrainDetailService service;

		 //constructor 
		 public TrainDetailController(TrainDetailService service) {
			super();
			this.service =service;
		 }
		
		 //Add Record Of Train
		 @PostMapping("/addTrain")
		 public ResponseEntity<TrainDetail> savePassenger(@RequestBody TrainDetail pass) throws TrainNoAlreadyExistsException{
			TrainDetail savePass=service.addTrainDetail(pass);
			return new ResponseEntity<>(savePass,HttpStatus.CREATED);
			
		}
		
		 //Get All Records Of Train
		 @GetMapping("/getAllTrain")
		 public ResponseEntity<List<TrainDetail>> getAllPassenger() {
			//log.info("Get All Records Of Passenger from getAllPassenger()");
			return new ResponseEntity<List<TrainDetail>>((List<TrainDetail>)service.getAllTrainDetail(),HttpStatus.OK);
			
		}
		
		 //Get Record Of Train by Train no.
		 @GetMapping("/getTrainById/{id}") 
		 public Optional<TrainDetail> getPassengerById(@PathVariable("id") int pid) throws TrainNoNotFound{
			 return service.getTrainDetailById(pid);
		   }
		
		 //Delete Train By Train no.
		 @DeleteMapping("/deleteTrainById/{pid}")
		 public ResponseEntity<Void> deletePassengerBypid(@PathVariable int pid)  throws TrainNoNotFound
		 {
			service.deleteTrainDetailBypid(pid);
			return ResponseEntity.noContent().build();
			
		}
		
	     // Update Train record
		 @PutMapping("/updateTrain/{pid}")
		 public ResponseEntity<TrainDetail> updatePassenger(@RequestBody TrainDetail pass,@PathVariable("pid") int pid) throws TrainNoNotFound  { 
			  Optional<TrainDetail> getPassengerById =service.getTrainDetailById(pid);
			  if(getPassengerById.isPresent()) {
				  TrainDetail savePass=service.updateTrainDetail(pass);
			      return new ResponseEntity<>(savePass,HttpStatus.OK);
			  }else
			  {
				 throw new TrainNoNotFound();
			  }}
}
