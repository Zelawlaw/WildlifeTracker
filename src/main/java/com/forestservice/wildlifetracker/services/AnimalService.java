package com.forestservice.wildlifetracker.services;

import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;

import java.util.List;

public interface AnimalService {

    List<Animal> getAllAnimals();

    void addAnimal(String name);

    void updateAnimal(Animal animal);

    void deleteAnimal(String name);

    // Additional methods for handling EndangeredAnimal
    List<EndangeredAnimal> getAllEndangeredAnimals();

    void addEndangeredAnimal(String name,String age,String health);

    void updateEndangeredAnimal(EndangeredAnimal endangeredAnimal);

}
