package com.forestservice.wildlifetracker.models.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("1")
@Data
public class EndangeredAnimal extends Animal {

    private String health;
    private String age;

    public EndangeredAnimal() {
        super();
    }

    public EndangeredAnimal(String name, String health, String age) {
        super(name);
        this.health = health;
        this.age = age;
    }

}
