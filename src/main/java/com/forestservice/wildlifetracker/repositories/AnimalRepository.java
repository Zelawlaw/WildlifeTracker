package com.forestservice.wildlifetracker.repositories;

import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // find all animals
    @Query("SELECT a FROM Animal a")
    List<Animal> findAllAnimals();

    // find all endangered animals
    @Query("SELECT ea FROM EndangeredAnimal ea")
    List<EndangeredAnimal> findAllEndangeredAnimals();
}
