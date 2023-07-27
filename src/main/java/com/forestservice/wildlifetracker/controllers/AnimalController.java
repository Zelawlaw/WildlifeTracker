package com.forestservice.wildlifetracker.controllers;


import com.forestservice.wildlifetracker.exceptions.AnimalExistsException;
import com.forestservice.wildlifetracker.models.entities.Animal;
import com.forestservice.wildlifetracker.services.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/animals")
    public String getAllAnimals(Model model) {
        model.addAttribute("animals", animalService.getAllAnimals());
        return "allAnimals";
    }

    @GetMapping("/endangeredAnimals")
    public String getAllEndangeredAnimals(Model model) {
        model.addAttribute("endangeredAnimals", animalService.getAllEndangeredAnimals());
        return "allEndangeredAnimals";
    }

    @GetMapping("/addanimal")
    public String getAddAnimalForm(Model model) {
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        return "addAnimalForm";
    }

    @PostMapping("/animal")
    public String addAnimal(@RequestParam("name") String name,
                            @RequestParam(value = "health", required = false) String health,
                            @RequestParam(value = "age", required = false) String age,
                            Model model) {
        try {
            log.info("adding endangered animal: {} ,health:{}, age :{} , health is empty :{}",name,health,age,health.isEmpty());
            // If health and age parameters are provided, it's an EndangeredAnimal
            if ((health != null  && !health.isEmpty())  && (age != null && !age.isEmpty())) {
                log.info("adding endangered animal: {}",name);
                animalService.addEndangeredAnimal(name, health, age);
            } else {
                log.info("adding ordinary animal: {}",name);
                animalService.addAnimal(name);
            }
            return "redirect:/animals";
        } catch (AnimalExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());

            return "errorPage";
        }
    }

    @GetMapping("/error")
    public String showErrorPage(@RequestParam("errorMessage") String errorMessage, Model model) {
        log.info("in error?");
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage";
    }
}


