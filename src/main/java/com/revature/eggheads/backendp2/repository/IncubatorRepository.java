package com.revature.eggheads.backendp2.repository;

import com.revature.eggheads.backendp2.model.Incubator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncubatorRepository extends JpaRepository<Incubator, Integer> {
}
