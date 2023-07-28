package com.forestservice.wildlifetracker.services;

import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class AnimalServiceTests {

    @MockBean
    AnimalRepository animalRepository;
    @Autowired
    private AnimalService animalService;

    @Test
    void testAddEndangeredAnimal_Success() throws AnimalExistsException {
        // Sample input data
        String name = "Panda";
        String age = "young";
        String health = "healthy";

        // Mock the behavior of the animalRepository to return an empty list of endangered animals with the given name
        when(animalRepository.findAnimalsByName(eq(name))).thenReturn(Collections.emptyList());

        // Mock the behavior of the animalRepository save method
        when(animalRepository.save(any(EndangeredAnimal.class))).thenReturn(null); // Return value not used in this test

        // Call the addEndangeredAnimal method
        animalService.addEndangeredAnimal(name, health, age);

        // Verify that the animalRepository.findEndangeredAnimalsByName is called once with the correct argument
        verify(animalRepository, times(1)).findAnimalsByName(eq(name));

        // Verify that the animalRepository.save is called once with the correct argument
        verify(animalRepository, times(1)).save(any(EndangeredAnimal.class));
    }

    @Test
    void testAddEndangeredAnimal_AnimalDoesntExists() {
        // Sample input data
        String name = "Panda";
        String age = "newborn";
        String health = "healthy";

        // Mock the behavior of the animalRepository to return a list containing an existing endangered animal with the given name
        when(animalRepository.findAnimalsByName(eq(name)))
                .thenReturn(Collections.singletonList(new EndangeredAnimal(name, health, age)));

        // Call the addEndangeredAnimal method and expect it to throw AnimalExistsException
        assertThrows(AnimalExistsException.class, () -> {
            animalService.addEndangeredAnimal(name, health, age);
        });

        // Verify that the animalRepository.findEndangeredAnimalsByName is called once with the correct argument
        verify(animalRepository, times(1)).findAnimalsByName(eq(name));

        // Verify that the animalRepository.save is not called
        verify(animalRepository, never()).save(any(EndangeredAnimal.class));
    }

    @Test
    public void testGetAllAnimals() {
        // Create some sample data
        Animal animal1 = new Animal("Lion");
        Animal animal2 = new Animal("Tiger");
        EndangeredAnimal endangeredAnimal1 = new EndangeredAnimal("Elephant", "HEALTHY", "ADULT");
        EndangeredAnimal endangeredAnimal2 = new EndangeredAnimal("Giraffe", "ILL", "YOUNG");

        // Define the behavior of the mocked repository
        List<Animal> allOrdinary = Arrays.asList(animal1, animal2);
        List<EndangeredAnimal> allEndangered = Arrays.asList(endangeredAnimal1, endangeredAnimal2);
        when(animalRepository.findAllNonEndangeredAnimals()).thenReturn(allOrdinary);
        when(animalRepository.findAllEndangeredAnimals()).thenReturn(allEndangered);

        // Call the method under test
        List<Object> allCombinedAnimals = animalService.getAllAnimals();
        System.out.println(animal1.toString());
        System.out.println(endangeredAnimal1.toString());
        // Assert the result
        assertThat(allCombinedAnimals).containsExactly(animal1, animal2, endangeredAnimal1, endangeredAnimal2);
    }


}
