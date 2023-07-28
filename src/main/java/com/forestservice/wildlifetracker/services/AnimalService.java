package com.forestservice.wildlifetracker.services;

import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;

import java.util.List;

public interface AnimalService {

    List<Object> getAllAnimals();

    void addAnimal(String name) throws AnimalExistsException;


    void deleteAnimal(String name);

    // Additional methods for handling EndangeredAnimal
    List<EndangeredAnimal> getAllEndangeredAnimals();

    void addEndangeredAnimal(String name, String health, String age) throws AnimalExistsException;


}
