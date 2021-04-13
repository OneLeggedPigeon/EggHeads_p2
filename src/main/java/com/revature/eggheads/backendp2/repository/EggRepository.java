package com.revature.eggheads.backendp2.repository;

import com.revature.eggheads.backendp2.model.Egg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EggRepository extends JpaRepository<Egg, Integer> {
}
