package br.com.rem_aya_2.integration_tests.vo.wrappers;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.rem_aya_2.integration_tests.vo.PlantVO;

public class PlantEmbeddedVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("plantVOList")
	private List<PlantVO> plants;
	
	public PlantEmbeddedVO (){}
	
	public List<PlantVO> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantVO> plants) {
		this.plants = plants;
	}

	@Override
	public int hashCode() {
		return Objects.hash(plants);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantEmbeddedVO other = (PlantEmbeddedVO) obj;
		return Objects.equals(plants, other.plants);
	}
	
		
}
