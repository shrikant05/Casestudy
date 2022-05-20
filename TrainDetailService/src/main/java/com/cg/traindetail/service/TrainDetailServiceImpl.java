package com.cg.traindetail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.traindetail.exception.TrainNoAlreadyExistsException;
import com.cg.traindetail.exception.TrainNoNotFound;
import com.cg.traindetail.model.TrainDetail;
import com.cg.traindetail.repository.TrainDetailRepository;

@Service
public class TrainDetailServiceImpl implements TrainDetailService {

	//Object of TrainDetailRepository to communicate with Repository
	private TrainDetailRepository repository;

	//constructor to pass TrainDetailRepository object as an argument
	public TrainDetailServiceImpl(TrainDetailRepository repository) {
		super();
		this.repository = repository;
	}

    //addTrainDetail Implementation
	@Override
	public TrainDetail addTrainDetail(TrainDetail pass) throws TrainNoAlreadyExistsException{
		if(repository.existsById(pass.getTrain_no())) {
			throw new TrainNoAlreadyExistsException();
			}
		TrainDetail saveTrain=repository.save(pass);
		return saveTrain;
	}

	//getAllTrainDetail Implementation
	@Override
	public List<TrainDetail> getAllTrainDetail() {
		return (List<TrainDetail>) repository.findAll();
	}

	//deleteTrainDetailBypid Implementation
	@Override
	public void deleteTrainDetailBypid(int pid) throws TrainNoNotFound {
			if(repository.existsById(pid)) {
				repository.deleteById(pid);
			}
			else {
				throw new  TrainNoNotFound();
			}
	}

	//updateTrainDetail Implementation
	@Override
	public TrainDetail updateTrainDetail(TrainDetail pass) throws TrainNoNotFound {
		TrainDetail updatePasseng=repository.save(pass);
		return updatePasseng;
	}

	//getTrainDetailById Implementation
	@Override
	public Optional<TrainDetail> getTrainDetailById(int pid) throws TrainNoNotFound{
		if(repository.existsById(pid)) {
				return repository.findById(pid);
			}
			else {
				throw new TrainNoNotFound();
			}}

 }
