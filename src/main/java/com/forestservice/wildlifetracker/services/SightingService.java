package com.forestservice.wildlifetracker.services;

import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Sighting;

import java.util.List;

public interface SightingService {

    List<Sighting> getAllSightings();

    List<Sighting> getAllEndangeredSightings();

    void addSighting(Long animalId, String location, String rangerName) throws AnimalExistsException;

    void deleteSighting (long sightingId);

}
