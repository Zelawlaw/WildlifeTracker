package com.forestservice.wildlifetracker.services.impl;

import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import com.forestservice.wildlifetracker.services.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public List<Object> getAllAnimals() {
        List<Object> allCombinedAnimals = new ArrayList<>();
        List<Animal> allOrdinary = this.animalRepository.findAllNonEndangeredAnimals();
        List<EndangeredAnimal> allendangered = this.animalRepository.findAllEndangeredAnimals();
        allCombinedAnimals.addAll(allOrdinary);
        allCombinedAnimals.addAll(allendangered);

        for(int i = 0;i<allCombinedAnimals.size();i++){
            log.info(allCombinedAnimals.get(i).toString());
        }

      return allCombinedAnimals;
    }


    @Override
    public void addAnimal(String name) throws AnimalExistsException {

        if (this.animalRepository.findAnimalsByName(name).size() >0 ) {
            throw new AnimalExistsException("Animal with name '" + name + "' already exists.");
        }

        Animal animal = new Animal(name);
        animalRepository.save(animal);

    }

    @Override
    public void deleteAnimal(String name) {
      this.animalRepository.deleteAll(this.animalRepository.findAnimalsByName(name));
    }

    @Override
    public List<EndangeredAnimal> getAllEndangeredAnimals() {
     return this.animalRepository.findAllEndangeredAnimals();
    }

    @Override
    public void addEndangeredAnimal(String name, String age, String health) throws AnimalExistsException {
        if (this.animalRepository.findAnimalsByName(name).size() >0 ) {
            throw new AnimalExistsException("Animal with name '" + name + "' already exists.");
        }

        Animal animal = new EndangeredAnimal(name,age,health);
        animalRepository.save(animal);
    }


}
