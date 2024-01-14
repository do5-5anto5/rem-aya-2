package br.com.rem_aya_2.data_vo_v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "planted_date", "in_house"})
public class PlantVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long key;
	@JsonProperty("name")
	private String name;
	@JsonProperty("planted_date")
	private Date plantedDate;
	@JsonProperty("in_house")
	private Boolean inHouse;
	
	public PlantVO() {}
	
	public Long getKey() {
		return key;
	}
	
	public void setKey(Long key) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getPlantedDate() {
		return plantedDate;
	}
	
	public void setPlantedDate(Date plantedDate) {
		this.plantedDate = plantedDate;
	}

	public Boolean getInHouse() {
		return inHouse;
	}

	public void setInHouse(Boolean inHouse) {
		this.inHouse = inHouse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(inHouse, key, name, plantedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantVO other = (PlantVO) obj;
		return Objects.equals(inHouse, other.inHouse) && Objects.equals(key, other.key)
				&& Objects.equals(name, other.name) && Objects.equals(plantedDate, other.plantedDate);
	}
}
