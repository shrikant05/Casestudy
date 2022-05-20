package com.cg.traindetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.traindetail.model.TrainDetail;

public interface TrainDetailRepository extends MongoRepository<TrainDetail,Integer>{

}
