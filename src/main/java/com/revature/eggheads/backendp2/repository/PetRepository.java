package com.revature.eggheads.backendp2.repository;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
