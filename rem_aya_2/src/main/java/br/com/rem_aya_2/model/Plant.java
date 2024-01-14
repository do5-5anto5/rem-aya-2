package br.com.rem_aya_2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plant")
public class Plant implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	@Column(name = "planted_date", nullable = false)	
	private Date plantedDate;
	@Column(name = "in_house", nullable = false)
	private Boolean inHouse;
	
	public Plant() {}
	
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

	@Override
	public int hashCode() {
		return Objects.hash(id, inHouse, name, plantedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		return Objects.equals(id, other.id) && Objects.equals(inHouse, other.inHouse)
				&& Objects.equals(name, other.name) && Objects.equals(plantedDate, other.plantedDate);
	}
}
