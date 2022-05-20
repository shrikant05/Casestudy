package com.cg.traindetail.service;

import java.util.List;
import java.util.Optional;

import com.cg.traindetail.exception.TrainNoAlreadyExistsException;
import com.cg.traindetail.exception.TrainNoNotFound;
import com.cg.traindetail.model.TrainDetail;

public interface TrainDetailService {
	public TrainDetail addTrainDetail(TrainDetail pass) throws TrainNoAlreadyExistsException;
	public void deleteTrainDetailBypid(int pid) throws TrainNoNotFound;
	public TrainDetail updateTrainDetail(TrainDetail pass) throws TrainNoNotFound;
	public Optional<TrainDetail> getTrainDetailById(int pid) throws TrainNoNotFound;; 
	public List<TrainDetail> getAllTrainDetail();
}
