package br.com.rem_aya_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.services.PlantService;
import br.com.rem_aya_2.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/plant/v1")
public class PlantController {

	@Autowired
	PlantService service;
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all Plants", description = "Finds all Plants", tags = {"Plants"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = { @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = PlantVO.class))
				)}
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)			
		}
	)
	public ResponseEntity<PagedModel<EntityModel<PlantVO>>> findAll(
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@RequestParam(value = "size", defaultValue = "12") Integer size,
		@RequestParam(value = "direction", defaultValue = "asc") String direction
	){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping(
		value = "/findPlantsByName/{name}",
		produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds Plants by Name", description = "Finds all Plants", tags = {"Plants"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = { @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = PlantVO.class))
				)}
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)			
		}
	)
	public ResponseEntity<PagedModel<EntityModel<PlantVO>>> findPlantsByName(
		@RequestParam(value = "name") String name,
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@RequestParam(value = "size", defaultValue = "12") Integer size,
		@RequestParam(value = "direction", defaultValue = "asc") String direction
		){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
		return ResponseEntity.ok(service.findPlantsByName(name, pageable));
	}
	
	@GetMapping(value = "/{id}",
		produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Plant", description = "Finds a Plant", tags = {"Plants"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = { @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = PlantVO.class))
				)}
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)			
		}
	)
	public PlantVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping(
		produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
		consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a new Plant", description = "Adds a new Plant, by passing JSON, XML or YML representation of Plant",
		tags = {"Plants"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = { @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = PlantVO.class))
				)}
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)			
		}
	)
	public PlantVO create(@RequestBody PlantVO plant) {
		return service.create(plant);
	}
	
	@PutMapping(
		produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
		consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a new Plant", description = "Updates a new Plant, by passing JSON, XML or YML representation of Plant",
	tags = {"Plants"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = { @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = PlantVO.class))
				)}
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)			
		}
	)
	public PlantVO update(@RequestBody PlantVO plant) {
		return service.update(plant);
	}
	
	@PatchMapping(
			value = "/{id}",
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Changes 'in house' property from a specific plant by id", description = "Changes 'in house' property from a specific plant by id",
	tags = {"Plants"}, 
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = { @Content(mediaType = "application/json", 
				array = @ArraySchema( schema = @Schema(implementation = PlantVO.class))
				)}
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)			
		}
	)
	public PlantVO changeInHouseProperty(@PathVariable(value = "id") Long id) {
		var vo = service.findById(id);
		return vo.getInHouse() ? service.changeInHouseToFalse(id) : service.changeInHouseToTrue(id);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Plant", description = "Deletes a Plant, by passing Id",
	tags = {"Plants"}, 
		responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)			
		}
	)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
