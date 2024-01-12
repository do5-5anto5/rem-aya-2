package br.com.rem_aya_2.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rem_aya_2.exceptions.ResourceNotFoundException;
import br.com.rem_aya_2.model.Plant;
import br.com.rem_aya_2.repositories.PlantRepository;

@Service
public class PlantService {
	
	@Autowired
	PlantRepository repository;
	
	private Logger logger = Logger.getLogger(PlantService.class.getName());
	
	public List<Plant> findAll(){
		
		logger.info("Finding all Plants!");
		
		return repository.findAll();
	}

	public Plant findById(Long id) {
		
		logger.info("Finding a Plant!");
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
	}
	
	public Plant create(Plant plant) {
		
		logger.info("Creating a Plant!");
		
		return repository.save(plant);
	}
	
	public Plant update(Plant plant) {
		
		logger.info("Updating a Plant!");
		
		var entity = repository.findById(plant.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		entity.setName(plant.getName());
		entity.setPlantedDate(plant.getPlantedDate());
		entity.setInHouse(plant.getInHouse());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one Plant!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		repository.delete(entity);
	}
}
