package com.forestservice.wildlifetracker.repositories;

import com.forestservice.wildlifetracker.models.entities.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {

}
