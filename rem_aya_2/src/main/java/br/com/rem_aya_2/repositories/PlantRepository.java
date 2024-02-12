package br.com.rem_aya_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rem_aya_2.model.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>{
	
	@Modifying
	@Query("UPDATE Plant p SET p.inHouse = false WHERE p.id =:id")
	void changeInHouseToFalse(@Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Plant p SET p.inHouse = true WHERE p.id =:id")
	void changeInHouseToTrue(@Param("id") Long id);
}
