package com.forestservice.wildlifetracker.services.impl;

import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.Sighting;
import com.forestservice.wildlifetracker.repositories.AnimalRepository;
import com.forestservice.wildlifetracker.repositories.SightingRepository;
import com.forestservice.wildlifetracker.services.SightingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Animal animal = this.animalRepository.findById(animalId).orElse(null);
        //check if animalId exists
     if(animal != null)
     {
         //create sighting
         Sighting newSighting = new Sighting(animal,location,rangerName);
         this.sightingRepository.save(newSighting);

     }else
     {
         throw new AnimalExistsException("Animal with ID:"+animalId+" does not exist");
     }

    }

    @Override
    public void deleteSighting(long sightingId) {
      this.sightingRepository.deleteById(sightingId);
    }
}
