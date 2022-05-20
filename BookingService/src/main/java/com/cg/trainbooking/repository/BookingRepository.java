package com.cg.trainbooking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.trainbooking.model.Booking;

public interface BookingRepository extends MongoRepository<Booking,Integer>{
}
