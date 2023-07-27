package com.forestservice.wildlifetracker.models.entities;


import com.forestservice.wildlifetracker.utility.AgeCategory;
import com.forestservice.wildlifetracker.utility.HealthStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("EndangeredAnimal") // Value for the "isendangered" discriminator column representing EndangeredAnimal
public class EndangeredAnimal extends Animal {

    @Enumerated(EnumType.STRING)
    private HealthStatus health;

    @Enumerated(EnumType.STRING)
    private AgeCategory age;



    public EndangeredAnimal(String name, String health, String age) {
        super(name);
        this.health = HealthStatus.valueOf(health.toUpperCase());
        this.age = AgeCategory.valueOf(age.toUpperCase());
    }

    @Override
    public String toString() {
        return "EndangeredAnimal(" + super.toString() + ", health=" + health + ", age=" + age + ")";
    }


}
