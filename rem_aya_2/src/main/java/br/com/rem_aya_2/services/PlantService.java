package br.com.rem_aya_2.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.rem_aya_2.controllers.PlantController;
import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.exceptions.RequiredObjectIsNullException;
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
		
		var plants = Mapper.parseObjectsList(repository.findAll(), PlantVO.class);
		plants.stream().forEach(p ->
		p.add(linkTo(methodOn(PlantController.class).findById(p.getKey())).withSelfRel()));
		
		return plants;
	}

	public PlantVO findById(Long id) {
		
		logger.info("Finding a Plant!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		var vo = Mapper.parseObject(entity, PlantVO.class);
		vo.add(linkTo(methodOn(PlantController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PlantVO create(PlantVO plant) {
		
		if (plant == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating a Plant!");
		
		var entity = Mapper.parseObject(plant, Plant.class);
		var vo = Mapper.parseObject(repository.save(entity), PlantVO.class);
		vo.add(linkTo(methodOn(PlantController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public PlantVO update(PlantVO plant) {
		
		if (plant == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating a Plant!");
		
		var entity = repository.findById(plant.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		entity.setName(plant.getName());
		entity.setPlantedDate(plant.getPlantedDate());
		entity.setInHouse(plant.getInHouse());
		
		var vo = Mapper.parseObject(repository.save(entity), PlantVO.class);
		vo.add(linkTo(methodOn(PlantController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
		
		
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one Plant!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		repository.delete(entity);
	}
}
