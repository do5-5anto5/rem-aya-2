package br.com.rem_aya_2.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.rem_aya_2.data_vo_v1.PlantVO;
import br.com.rem_aya_2.model.Plant;

public class Mapper {
	
	private static ModelMapper mapper = new ModelMapper();
	
	static {
		mapper.createTypeMap(
				Plant.class,
				PlantVO.class)
			.addMapping(Plant::getId, PlantVO::setKey);
	}
	
	static {
		mapper.createTypeMap(
				PlantVO.class,
				Plant.class)
			.addMapping(PlantVO::getKey, Plant::setId);
	}
	
	public static <O, D> D parseObject(O origin, Class<D> destination){
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseObjectsList(List<O> origin, Class<D> destination){

		List<D> destinationObjects = new ArrayList<D>();
		for(O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		
		return destinationObjects;
	}

}
