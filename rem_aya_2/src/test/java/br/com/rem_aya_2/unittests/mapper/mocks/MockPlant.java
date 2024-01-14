package br.com.rem_aya_2.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.model.Plant;

public class MockPlant {
	
	 public Plant mockEntity() {
	        return mockEntity(0);
	    }
	    
	    public PlantVO mockVO() {
	        return mockVO(0);
	    }
	    
	    public List<Plant> mockEntityList() {
	        List<Plant> plants = new ArrayList<Plant>();
	        for (int i = 0; i < 14; i++) {
	            plants.add(mockEntity(i));
	        }
	        return plants;
	    }

	    public List<PlantVO> mockVOList() {
	        List<PlantVO> plants = new ArrayList<>();
	        for (int i = 0; i < 14; i++) {
	            plants.add(mockVO(i));
	        }
	        return plants;
	    }
	    
	    public Plant mockEntity(Integer number) {
	        Plant plant = new Plant();
	        plant.setId(number.longValue());
	        plant.setName("Name Test" + number);
	        plant.setPlantedDate(new Date(2024-01-13));
	        plant.setInHouse(false);
	        return plant;
	    }

	    public PlantVO mockVO(Integer number) {
	        PlantVO plant = new PlantVO();
	        plant.setKey(number.longValue());
	        plant.setName("Name Test" + number);
	        plant.setPlantedDate(new Date(2024-01-13));
	        plant.setInHouse(false);
	        return plant;
	    }
}
