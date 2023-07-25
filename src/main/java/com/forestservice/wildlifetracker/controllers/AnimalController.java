package com.forestservice.wildlifetracker.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AnimalController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("animal","zeanimal");
        return "index";
    }
}
