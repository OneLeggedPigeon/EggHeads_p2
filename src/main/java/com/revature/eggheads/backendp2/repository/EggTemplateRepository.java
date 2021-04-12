package com.revature.eggheads.backendp2.repository;

import com.revature.eggheads.backendp2.model.EggTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface EggTemplateRepository extends JpaRepository<EggTemplate, Integer> {

    @Query(value = "FROM EggTemplate WHERE animalType = ?1")
    List<EggTemplate> findByAnimalType(String str);

//    @Query(value = "SELECT * FROM egg_template et WHERE et.animal_type = :str", nativeQuery = true)
//    List<EggTemplate> findByAnimalType(String str);

    @Query(value = "SELECT * FROM egg_template WHERE incubation_period <= ?1", nativeQuery = true)
    List<EggTemplate> findByIncubationPeriodLessThanEqual(int period);
}
