package com.forestservice.wildlifetracker.services;

import com.forestservice.wildlifetracker.models.entities.Sighting;

import java.util.List;

public interface SightingService {

    List<Sighting> getAllSightings();

    void addSighting( int animalId, String location, String rangerName);

    void deleteSighting (int sightingId);

}
