package com.forestservice.wildlifetracker.repositorytests;

import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import com.forestservice.wildlifetracker.models.entities.Sighting;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import com.forestservice.wildlifetracker.repositories.SightingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SightingRepositoryTests {

    @Autowired
    SightingRepository sightingRepository;

    @Autowired
    AnimalRepository animalRepository;
    Animal animal1,animal2,endangeredAnimal;

    @BeforeEach
    public void setupData() {
        //sample animals
        animal1 = new Animal("Lion-test");
       animal2 = new Animal("Elephant-test");
        endangeredAnimal = new EndangeredAnimal("Tiger-test", "healthy", "adult");

        animalRepository.save(animal1);
        animalRepository.save(animal2);
        animalRepository.save(endangeredAnimal);
    }

    @Test
    @Transactional  // roll back db transaction after test completes
    public void testSavingSightingSuccess(){
        //add sighting of  Lion-Test
        Sighting lionsighting = new Sighting(animal1, "Tsavo", "Zelawlaw");

        //add sighting of  Tiger-Test
        Sighting tigersighting = new Sighting(endangeredAnimal, "India", "Zelawlaw");
        this.sightingRepository.save(lionsighting);
        this.sightingRepository.save(tigersighting);

         //find all sightings
        assertEquals(2,this.sightingRepository.findAll().size());

        //sightings of endangered animals
        assertEquals(1,this.sightingRepository.findAllEndangeredSightings().size());

        //confirm reported time is not null
        assertNotNull(this.sightingRepository.findAll().get(0).getReportedTime());
    }

    @AfterEach
    public void cleanupData() {

        // Find all animals with the '-test' suffix
        List<Animal> animalsToRemove = animalRepository.findAnimalsByNameEndingWith("-test");
       // animalRepository.deleteAll(animalsToRemove);
    }
}
