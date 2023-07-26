package com.forestservice.wildlifetracker.models.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("EndangeredAnimal") // Value for the "isendangered" discriminator column representing EndangeredAnimal
public class EndangeredAnimal extends Animal {

    private String health;
    private String age;



    public EndangeredAnimal(String name, String health, String age) {
        super(name);
        this.health = health;
        this.age = age;
    }


}
