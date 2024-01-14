package br.com.rem_aya_2.unittests.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.mapper.Mapper;
import br.com.rem_aya_2.model.Plant;
import br.com.rem_aya_2.unittests.mapper.mocks.MockPlant;

public class MapperTest {
	
	MockPlant inputObject;
	
	@BeforeEach
	public void setUp() {
		inputObject = new MockPlant();
	}
	
	@Test
	public void parseEntityToVOTest() {
		
		var output = Mapper.parseObject(inputObject.mockEntity(), PlantVO.class);
		
		assertEquals(Long.valueOf(0L), output.getKey());
		assertEquals("Name Test0", output.getName());
		assertEquals(false, output.getInHouse());
		assertNotNull(output.getPlantedDate());
	}
	
	@Test
	public void parseEntityListToVOTest() {
		var outputList = Mapper.parseObjectsList(inputObject.mockEntityList(), PlantVO.class);
		
		var output0 = outputList.get(0);
		assertEquals(Long.valueOf(0L), output0.getKey());
		assertEquals("Name Test0", output0.getName());
		assertEquals(false, output0.getInHouse());
		assertNotNull(output0.getPlantedDate());
		
		var output7 = outputList.get(7);
		assertEquals(Long.valueOf(7L), output7.getKey());
		assertEquals("Name Test7", output7.getName());
		assertEquals(false, output7.getInHouse());
		assertNotNull(output7.getPlantedDate());
		
		var output13 = outputList.get(13);
		assertEquals(Long.valueOf(13L), output13.getKey());
		assertEquals("Name Test13", output13.getName());
		assertEquals(false, output13.getInHouse());
		assertNotNull(output13.getPlantedDate());
	}
	
	@Test
	public void parseVOToEntityTest() {
		
		var output = Mapper.parseObject(inputObject.mockVO(), Plant.class);
		
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("Name Test0", output.getName());
		assertEquals(false, output.getInHouse());
		assertNotNull(output.getPlantedDate());
	}
	
	@Test
	public void parseVOListToEntityTest() {
		var outputList = Mapper.parseObjectsList(inputObject.mockVOList(), Plant.class);
		
		var output0 = outputList.get(0);
		assertEquals(Long.valueOf(0L), output0.getId());
		assertEquals("Name Test0", output0.getName());
		assertEquals(false, output0.getInHouse());
		assertNotNull(output0.getPlantedDate());
		
		var output7 = outputList.get(7);
		assertEquals(Long.valueOf(7L), output7.getId());
		assertEquals("Name Test7", output7.getName());
		assertEquals(false, output7.getInHouse());
		assertNotNull(output7.getPlantedDate());
		
		var output13 = outputList.get(13);
		assertEquals(Long.valueOf(13L), output13.getId());
		assertEquals("Name Test13", output13.getName());
		assertEquals(false, output13.getInHouse());
		assertNotNull(output13.getPlantedDate());
	}
}
