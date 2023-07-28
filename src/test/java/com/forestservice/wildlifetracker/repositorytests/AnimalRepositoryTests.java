package com.forestservice.wildlifetracker.repositorytests;

import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AnimalRepositoryTests {

    @Autowired
    private AnimalRepository animalRepository;


    @BeforeEach
    public void setupData() {
        Animal animal1 = new Animal("Lion-test");
        Animal animal2 = new Animal("Elephant-test");
        Animal endangeredAnimal = new EndangeredAnimal("Tiger-test", "healthy", "adult");

        animalRepository.save(animal1);
        animalRepository.save(animal2);
        animalRepository.save(endangeredAnimal);
    }

    @Test
    public void testFindAllAnimals() {
        List<Animal> animals = animalRepository.findAnimalsByNameEndingWith("-test");
        assertEquals(3,animals.size());

    }

    @Test
    public void testFindAllEndangeredAnimals() {
        List<EndangeredAnimal> endangeredAnimals = animalRepository.findAllEndangeredAnimals();
        assertEquals(1,endangeredAnimals.size());

    }


    @Test
    public void testFindAllNonEndangeredAnimals() {
        List<Animal> nonendangeredAnimals = animalRepository.findAllNonEndangeredAnimals();
        assertEquals(2,nonendangeredAnimals.size());

    }


    @AfterEach
    public void cleanupData() {
        // Find all animals with the '-test' suffix
        List<Animal> animalsToRemove = animalRepository.findAnimalsByNameEndingWith("-test");
        animalRepository.deleteAll(animalsToRemove);
    }
}
