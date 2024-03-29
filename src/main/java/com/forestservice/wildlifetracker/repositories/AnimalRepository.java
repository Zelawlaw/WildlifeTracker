package com.forestservice.wildlifetracker.repositories;

import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // find all animals
    @Query("SELECT a FROM Animal a")
    List<Animal> findAllAnimals();

    // find all endangered animals
    @Query("SELECT ea FROM EndangeredAnimal ea")
    List<EndangeredAnimal> findAllEndangeredAnimals();

    @Query("SELECT ea FROM EndangeredAnimal ea WHERE ea.name = :name")
    List<EndangeredAnimal> findEndangeredAnimalsByName(@Param("name") String name);

    @Query("SELECT ea FROM EndangeredAnimal ea WHERE ea.id = :id")
    Optional<EndangeredAnimal> findEndangeredAnimalById(@Param("id") Long id);

    List<Animal> findAnimalsByNameEndingWith(String s);

    List<Animal> findAnimalsByName(String s);

    @Query(value = "SELECT * FROM animals WHERE isendangered = 'Animal'", nativeQuery = true)
    List<Animal> findAllNonEndangeredAnimals();

}

