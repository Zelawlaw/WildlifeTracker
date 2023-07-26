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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class AnimalServiceTests {

    @Autowired
    private AnimalService animalService;

    @Test
    @Transactional
    public void testFindAllAnimalsSuccess() throws AnimalExistsException {
        this.animalService.addAnimal("animal A-test");
        this.animalService.addAnimal("animal B-test");
        this.animalService.addAnimal("animal C-test");

        assertEquals(3,this.animalService.getAllAnimals().size());
    }

    @Test
    @Transactional
    public void testFindAllAnimalsFailure() {
       try {
           this.animalService.addAnimal("animal A-test");
           this.animalService.addAnimal("animal A-test");
           throw new Exception("Test failing");
       }catch(Exception ex){
           assertTrue(ex.getMessage().contains("Animal with name ") );
       }
    }

    @Test
    @Transactional
    public void testFindAllEndangeredAnimalsSuccess() throws AnimalExistsException {
        this.animalService.addEndangeredAnimal("animal A-test","healthy","newborn");
        this.animalService.addEndangeredAnimal("animal B-test","healthy","newborn");

        assertEquals(2,this.animalService.getAllEndangeredAnimals().size());
    }



}
