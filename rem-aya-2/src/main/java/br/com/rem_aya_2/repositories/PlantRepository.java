package br.com.rem_aya_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rem_aya_2.model.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>{}
