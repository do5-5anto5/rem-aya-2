package br.com.rem_aya_2.integration_tests.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.rem_aya_2.integration_tests.testcontainers.AbstractIntegrationTest;
import br.com.rem_aya_2.model.Plant;
import br.com.rem_aya_2.repositories.PlantRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class PlantRepositoryTest extends AbstractIntegrationTest{

	@Autowired
	PlantRepository repository;
	
	private static Plant plant;
	
	@BeforeAll
	public static void setup() {
		plant = new Plant();
	}
	
	@Test
	@Order(0)
	public void testFindPlantsByName() throws JsonMappingException, JsonProcessingException{
		
		Pageable pageable = PageRequest.of(0, 6, Sort.by(Direction.ASC, "name"));
		
		plant = repository.findPlantsByName("be", pageable).getContent().get(0);
		
		assertNotNull(plant.getId());
		assertNotNull(plant.getName());
		assertNotNull(plant.getPlantedDate());
		assertNotNull(plant.getInHouse());
		assertNotNull(plant.getAddress());
		
		assertEquals(422, plant.getId());
		assertEquals("Adirondack Blackberry", plant.getName());
		assertEquals(false, plant.getInHouse());
		assertEquals("4271 Washington Way", plant.getAddress());
	}
	
	@Test
	@Order(1)
	public void testChangeInHouseToTrue() throws JsonMappingException, JsonProcessingException{
		
		repository.changeInHouseToTrue(plant.getId());
		
		Pageable pageable = PageRequest.of(0, 6, Sort.by(Direction.ASC, "name"));		
		plant = repository.findPlantsByName("be", pageable).getContent().get(0);
		
		assertNotNull(plant.getId());
		assertNotNull(plant.getName());
		assertNotNull(plant.getPlantedDate());
		assertNotNull(plant.getInHouse());
		assertNotNull(plant.getAddress());
		
		assertEquals(422, plant.getId());
		assertEquals("Adirondack Blackberry", plant.getName());
		assertEquals(true, plant.getInHouse());
		assertEquals("4271 Washington Way", plant.getAddress());
	}
	
	@Test
	@Order(2)
	public void testChangeInHouseToFalse() throws JsonMappingException, JsonProcessingException{
		
		repository.changeInHouseToFalse(plant.getId());
		
		Pageable pageable = PageRequest.of(0, 6, Sort.by(Direction.ASC, "name"));		
		plant = repository.findPlantsByName("be", pageable).getContent().get(0);
		
		assertNotNull(plant.getId());
		assertNotNull(plant.getName());
		assertNotNull(plant.getPlantedDate());
		assertNotNull(plant.getInHouse());
		assertNotNull(plant.getAddress());
		
		assertEquals(422, plant.getId());
		assertEquals("Adirondack Blackberry", plant.getName());
		assertEquals(false, plant.getInHouse());
		assertEquals("4271 Washington Way", plant.getAddress());
	}
}
