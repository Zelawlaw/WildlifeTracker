package com.forestservice.wildlifetracker.controllers;

import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.models.entities.Sighting;
import com.forestservice.wildlifetracker.services.AnimalService;
import com.forestservice.wildlifetracker.services.SightingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SightingController {

    private final SightingService sightingService;
    private final AnimalService animalService;

    @GetMapping("/addSighting")
    public String showAddSightingForm(Model model) {
        model.addAttribute("sighting", new Sighting());
        model.addAttribute("animals", animalService.getAllAnimals()); // Assuming you have a method to get all animals
        return "addSightingForm";
    }

    @PostMapping("/addSighting")
    public String addSighting(@ModelAttribute Sighting sighting) throws AnimalExistsException {
        sightingService.addSighting(sighting.getAnimal().getId(), sighting.getLocation(), sighting.getRangerName());
        return "redirect:/";
    }

    @GetMapping("/")
    public String listAllSightings(Model model) {
        model.addAttribute("sightings", sightingService.getAllSightings());
        return "listSightings";
    }

    @PostMapping("/deleteSighting/{sightingId}")
    public String deleteSighting(@PathVariable("sightingId") long sightingId) {
        sightingService.deleteSighting(sightingId);
        return "redirect:/";
    }
}

