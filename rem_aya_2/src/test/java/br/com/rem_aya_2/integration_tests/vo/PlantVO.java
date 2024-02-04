package br.com.rem_aya_2.integration_tests.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlantVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Date plantedDate;
	private Boolean inHouse;
	private String address;
	
	public PlantVO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return Objects.hash(address, id, inHouse, name, plantedDate);
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
		return Objects.equals(address, other.address) && Objects.equals(id, other.id)
				&& Objects.equals(inHouse, other.inHouse) && Objects.equals(name, other.name)
				&& Objects.equals(plantedDate, other.plantedDate);
	}

}