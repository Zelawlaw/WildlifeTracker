package com.forestservice.wildlifetracker.services.impl;

import com.forestservice.wildlifetracker.models.entities.Sighting;
import com.forestservice.wildlifetracker.services.SightingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightingServiceImpl implements SightingService {
    @Override
    public List<Sighting> getAllSightings() {
        return null;
    }

    @Override
    public void addSighting(int animalId, String location, String rangerName) {

    }

    @Override
    public void deleteSighting(int sightingId) {

    }
}
