package com.marianagv.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marianagv.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
