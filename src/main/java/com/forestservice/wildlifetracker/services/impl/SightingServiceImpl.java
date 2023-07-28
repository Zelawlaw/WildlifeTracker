package com.forestservice.wildlifetracker.services.impl;

import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.EndangeredAnimal;
import com.forestservice.wildlifetracker.models.entities.Sighting;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import com.forestservice.wildlifetracker.repositories.SightingRepository;
import com.forestservice.wildlifetracker.services.SightingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SightingServiceImpl implements SightingService {

    private final SightingRepository sightingRepository;
    private final AnimalRepository animalRepository;

    @Override
    public List<Sighting> getAllSightings() {
        return this.sightingRepository.findAll();
    }

    @Override
    public List<Sighting> getAllEndangeredSightings() {
        return this.sightingRepository.findAllEndangeredSightings();
    }


    @Override
    public void addSighting(Long animalId, String location, String rangerName) throws AnimalExistsException {
        Optional<Animal> animal = this.animalRepository.findById(animalId);
        Optional<EndangeredAnimal> endangeredAnimal = this.animalRepository.findEndangeredAnimalById(animalId);
        Sighting newSighting;

        if (endangeredAnimal.isPresent()) {
            log.info("endangered sighting");
            newSighting = new Sighting(endangeredAnimal.get(), location, rangerName);
        } else if (animal.isPresent()) {
            log.info("ordinary sighting");
            newSighting = new Sighting(animal.get(), location, rangerName);
        } else {
            throw new AnimalExistsException("Animal with ID:" + animalId + " does not exist");
        }

        this.sightingRepository.save(newSighting);

    }

    @Override
    public void deleteSighting(long sightingId) {
      this.sightingRepository.deleteById(sightingId);
    }
}
