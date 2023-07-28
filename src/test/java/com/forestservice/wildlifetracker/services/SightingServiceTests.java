package com.forestservice.wildlifetracker.services;


import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.Sighting;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import com.forestservice.wildlifetracker.repositories.SightingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SightingServiceTests {


    @MockBean
    private AnimalRepository animalRepository;

    @MockBean
    private SightingRepository sightingRepository;

    @Autowired
    private SightingService sightingService;


    @Test
    void testAddSighting_AnimalExists() throws AnimalExistsException {
        // Create a sample animal and sighting data
        Long animalId = 1L;
        String location = "Tsavo";
        String rangerName = "Zelawlaw";
        Animal animal = new Animal("Lion");
        Sighting newSighting = new Sighting(animal, location, rangerName);

        // Mock the behavior of the animalRepository
        when(animalRepository.findById(any(Long.class))).thenReturn(Optional.of(animal));

        // Mock the behavior of the sightingRepository
        when(sightingRepository.save(any(Sighting.class))).thenReturn(newSighting);

        // Call the addSighting method
        sightingService.addSighting(animalId, location, rangerName);

        // Verify that the animalRepository.findById is called once with the correct argument
        verify(animalRepository, times(1)).findById(animalId);

        // Verify that the sightingRepository.save is called once with the correct argument
        verify(sightingRepository, times(1)).save(any(Sighting.class));

    }

    @Test
    void testAddSighting_AnimalNotExists() {
        // Create a sample animal and sighting data
        Long animalId = 1L;
        String location = "Tsavo";
        String rangerName = "Zelawlaw";

        // Mock the behavior of the animalRepository to return an empty optional
        when(animalRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(animalRepository.findEndangeredAnimalById(any(Long.class))).thenReturn(Optional.empty());

        // Call the addSighting method and expect it to throw AnimalExistsException
        assertThrows(AnimalExistsException.class, () -> {
            sightingService.addSighting(animalId, location, rangerName);
        });

        // Verify that the animalRepository.findById is called once with the correct argument
        verify(animalRepository, times(1)).findById(animalId);

        // Verify that the sightingRepository.save is not called
        verify(sightingRepository, never()).save(any(Sighting.class));
    }

}
