package com.forestservice.wildlifetracker.models.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("endangered") // Value for the "is_endangered" discriminator column representing EndangeredAnimal
public class EndangeredAnimal extends Animal {

    private String health;
    private String age;



    public EndangeredAnimal(String name, String health, String age) {
        super(name);
        this.health = health;
        this.age = age;
    }


}
