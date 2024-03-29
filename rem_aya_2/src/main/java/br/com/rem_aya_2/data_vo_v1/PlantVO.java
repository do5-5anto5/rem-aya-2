package br.com.rem_aya_2.data_vo_v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "plantedDate", "inHouse", "address"})
public class PlantVO extends RepresentationModel<PlantVO> implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long key;
	private String name;
	private Date plantedDate;
	private Boolean inHouse;
	private String address;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(address, inHouse, key, name, plantedDate);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantVO other = (PlantVO) obj;
		return Objects.equals(address, other.address) && Objects.equals(inHouse, other.inHouse)
				&& Objects.equals(key, other.key) && Objects.equals(name, other.name)
				&& Objects.equals(plantedDate, other.plantedDate);
	}
}
