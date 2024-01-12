package br.com.rem_aya_2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rem_aya_2.model.Plant;
import br.com.rem_aya_2.services.PlantService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/plant")
public class PlantController {

	@Autowired
	PlantService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Plant> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public Plant findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public Plant createPlant(@RequestBody Plant plant) {
		return service.create(plant);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			 	consumes = MediaType.APPLICATION_JSON_VALUE)
	public Plant updatePlant(@RequestBody Plant plant) {
		return service.update(plant);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
