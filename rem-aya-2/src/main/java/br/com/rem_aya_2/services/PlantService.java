package br.com.rem_aya_2.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.exceptions.ResourceNotFoundException;
import br.com.rem_aya_2.mapper.Mapper;
import br.com.rem_aya_2.model.Plant;
import br.com.rem_aya_2.repositories.PlantRepository;

@Service
public class PlantService {
	
	@Autowired
	PlantRepository repository;
	
	private Logger logger = Logger.getLogger(PlantService.class.getName());
	
	public List<PlantVO> findAll(){
		
		logger.info("Finding all Plants!");
		
		return Mapper.parseObjectsList(repository.findAll(), PlantVO.class);
	}

	public PlantVO findById(Long id) {
		
		logger.info("Finding a Plant!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		return Mapper.parseObject(entity, PlantVO.class);
	}
	
	public PlantVO create(PlantVO plant) {
		
		logger.info("Creating a Plant!");
		
		var entity = Mapper.parseObject(plant, Plant.class);
		var vo = Mapper.parseObject(repository.save(entity), PlantVO.class);
		
		return vo;
	}
	
	public PlantVO update(PlantVO plant) {
		
		logger.info("Updating a Plant!");
		
		var entity = repository.findById(plant.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		entity.setName(plant.getName());
		entity.setPlantedDate(plant.getPlantedDate());
		entity.setInHouse(plant.getInHouse());
		
		var vo = Mapper.parseObject(repository.save(entity), PlantVO.class);
		
		return vo;
		
		
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one Plant!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		repository.delete(entity);
	}
}
