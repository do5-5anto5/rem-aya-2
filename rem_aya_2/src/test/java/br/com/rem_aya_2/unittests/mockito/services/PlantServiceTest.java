package br.com.rem_aya_2.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.exceptions.RequiredObjectIsNullException;
import br.com.rem_aya_2.model.Plant;
import br.com.rem_aya_2.repositories.PlantRepository;
import br.com.rem_aya_2.services.PlantService;
import br.com.rem_aya_2.unittests.mapper.mocks.MockPlant;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PlantServiceTest {

	MockPlant input;
	
	@InjectMocks
	private PlantService service;
	
	@Mock
	PlantRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPlant();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindById() {
		Plant entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		testVOAssertions(result);
	}
	
	@Test
	void testCreate() {
		Plant persisted = input.mockEntity(1);
		persisted.setId(1L);
		
		PlantVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(any(Plant.class))).thenReturn(persisted);
		
		var result = service.create(vo);
		
		testVOAssertions(result);
		
	}
	
	@Test
	void testCreateWitNullException() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, 
				() -> service.create(null)
				);
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testUpdate() {
		Plant entity = input.mockEntity(1);
		
		Plant persisted = entity;
		persisted.setId(1L);
		
		PlantVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		testVOAssertions(result);
	}
	
	@Test
	void TestUpdateWithNullException() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, 
				() -> service.update(null)
				);
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	
	}
	
	@Test
	void testDelete() {
		Plant entity  = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}
	
	@Test
	void TestFindAll() {
		List<Plant>list = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(list);
		
		var plants = service.findAll();
		
		assertNotNull(plants);
		assertEquals(14, plants.size());
		
		var plantOne = plants.get(1);
		testVOListAssertions(plantOne, 1);
		
		var plantSeven = plants.get(7);
		
		testVOListAssertions(plantSeven, 7);
		
		var plantNine = plants.get(9);
		testVOListAssertions(plantNine, 9);
	}
	
	void testVOAssertions(PlantVO result) {
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/plant/v1/1>;rel=\"self\"]"));
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Name Test1", result.getName());
		assertEquals(false, result.getInHouse());
		assertNotNull(result.getPlantedDate());
	}
	
	void testVOListAssertions(PlantVO plant, Integer number) {
		assertNotNull(plant);
		assertNotNull(plant.getKey());
		assertNotNull(plant.getLinks());
		
		assertTrue(plant.toString().contains("links: [</api/plant/v1/"+number+">;rel=\"self\"]"));
		assertEquals("Address Test" + number, plant.getAddress());
		assertEquals("Name Test" + number, plant.getName());
		assertEquals(false, plant.getInHouse());
		assertNotNull(plant.getPlantedDate());
		
	}
}
