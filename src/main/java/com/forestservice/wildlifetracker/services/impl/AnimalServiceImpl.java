package com.forestservice.wildlifetracker.services.impl;

import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import com.forestservice.wildlifetracker.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public List<Animal> getAllAnimals() {
        return this.animalRepository.findAll();
    }

    @Override
    public void addAnimal(String name) {



    }

    @Override
    public void updateAnimal(Animal animal) {

    }

    @Override
    public void deleteAnimal(String name) {

    }

    @Override
    public List<EndangeredAnimal> getAllEndangeredAnimals() {

    }

    @Override
    public void addEndangeredAnimal(String name, String age, String health) {

    }

    @Override
    public void updateEndangeredAnimal(EndangeredAnimal endangeredAnimal) {

    }
}
