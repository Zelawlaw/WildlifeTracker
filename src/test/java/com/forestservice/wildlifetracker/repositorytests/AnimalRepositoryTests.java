package com.forestservice.wildlifetracker.repositorytests;

import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest
@SpringBootTest
public class AnimalRepositoryTests {

    @Autowired
    private AnimalRepository animalRepository;

    // Test data setup using @BeforeEach
    @BeforeEach
    public void setupData() {
        Animal animal1 = new Animal("Lion-test");
        Animal animal2 = new Animal("Elephant-test");
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Tiger-test", "healthy", "adult");

        animalRepository.save(animal1);
        animalRepository.save(animal2);
        animalRepository.save(endangeredAnimal);
    }

    @Test
    public void testFindAllAnimals() {
        List<Animal> animals = animalRepository.findAllAnimals();
        assertEquals(3,animals.size());

    }

    @Test
    public void testFindAllEndangeredAnimals() {
        List<EndangeredAnimal> endangeredAnimals = animalRepository.findAllEndangeredAnimals();
        assertEquals(1,endangeredAnimals.size());

    }

    // Sample data deletion using @AfterEach
    @AfterEach
    public void cleanupData() {
        // Find all animals with the '-test' suffix
        List<Animal> animalsToRemove = animalRepository.findAnimalsByNameEndingWith("-test");
        animalRepository.deleteAll(animalsToRemove);
    }
}
