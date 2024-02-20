package br.com.rem_aya_2.integration_tests.vo.wrappers;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PlantVOWrapper")
public class PlantVOWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("_embedded")
	private PlantEmbeddedVO plantEmbedded;
	
	public PlantVOWrapper(){}

	public PlantEmbeddedVO getPlantEmbedded() {
		return plantEmbedded;
	}

	public void setPlantEmbedded(PlantEmbeddedVO plantEmbeded) {
		this.plantEmbedded = plantEmbeded;
	}

	@Override
	public int hashCode() {
		return Objects.hash(plantEmbedded);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantVOWrapper other = (PlantVOWrapper) obj;
		return Objects.equals(plantEmbedded, other.plantEmbedded);
	}

}
