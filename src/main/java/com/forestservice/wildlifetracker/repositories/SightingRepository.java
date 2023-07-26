package com.forestservice.wildlifetracker.repositories;

import com.forestservice.wildlifetracker.models.entities.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {

    // Find all sightings of endangered animals
    @Query("SELECT s FROM Sighting s WHERE TYPE(s.animal) = EndangeredAnimal")
    List<Sighting> findAllEndangeredSightings();

    //find all animals with suffix, to use for test
    @Query("SELECT s FROM Sighting s WHERE s.animal.name LIKE %:nameSuffix")
    List<Sighting> findAllByAnimalNameEndingWithTest(String nameSuffix);
}
