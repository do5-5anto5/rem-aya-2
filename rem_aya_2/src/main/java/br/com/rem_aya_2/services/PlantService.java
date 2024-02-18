package br.com.rem_aya_2.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rem_aya_2.controllers.PlantController;
import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.exceptions.RequiredObjectIsNullException;
import br.com.rem_aya_2.exceptions.ResourceNotFoundException;
import br.com.rem_aya_2.mapper.Mapper;
import br.com.rem_aya_2.model.Plant;
import br.com.rem_aya_2.repositories.PlantRepository;
import jakarta.transaction.Transactional;

@Service
public class PlantService {
	
	@Autowired
	PlantRepository repository;
	
	private Logger logger = Logger.getLogger(PlantService.class.getName());
	
	public Page<PlantVO> findAll(Pageable pageable){
		
		logger.info("Finding all Plants!");		
		var plantsPage = repository.findAll(pageable);		
		var plantVOsPage = plantsPage.map(p -> mapperParse(p));		
		plantVOsPage.map(p -> addLink(p));		
		return plantVOsPage;
	}

	public PlantVO findById(Long id) {
		
		logger.info("Finding a Plant!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		var vo = Mapper.parseObject(entity, PlantVO.class);
		addLink(vo);
		return vo;
	}
	
	public PlantVO create(PlantVO plant) {
		
		if (plant == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating a Plant!");
		
		var entity = Mapper.parseObject(plant, Plant.class);
		var vo = mapperParse(repository.save(entity));
		addLink(vo);
		
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
		
		var vo = mapperParse(repository.save(entity));
		addLink(vo);
		
		return vo;
	}
	
	@Transactional
	public PlantVO changeInHouseToTrue(Long id) {
		
		repository.changeInHouseToTrue(id);

		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		var vo = mapperParse(entity);
		addLink(vo);
		
		return vo;
	}
	
	@Transactional
	public PlantVO changeInHouseToFalse(Long id) {
		
		repository.changeInHouseToFalse(id);
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		var vo = mapperParse(entity);
		addLink(vo);
		
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one Plant!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		repository.delete(entity);
	}
	
	private PlantVO addLink (PlantVO vo) {
		return vo.add(linkTo(methodOn(PlantController.class).findById(vo.getKey())).withSelfRel());
	}
	
	private PlantVO mapperParse(Plant entity) {
		return Mapper.parseObject(entity, PlantVO.class);
	}

}
