package com.forestservice.wildlifetracker.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "sightings")
@Data
@NoArgsConstructor
public class Sighting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal; // Refers to both Animal and EndangeredAnimal

    private boolean isEndangered;
    private String location;
    private String rangerName;

    @Basic(optional = false)
    @Column(name = "reportedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedTime;


    // Constructor for regular Animal sighting
    public Sighting(Animal animal, String location, String rangerName) {
        this.animal = animal;
        this.location = location;
        this.rangerName = rangerName;
        this.isEndangered = false;
        this.reportedTime = new Date();
    }

    // Constructor for EndangeredAnimal sighting
    public Sighting(EndangeredAnimal endangeredAnimal, String location, String rangerName) {
        this.animal = endangeredAnimal;
        this.location = location;
        this.rangerName = rangerName;
        this.isEndangered = true;
        this.reportedTime = new Date();
    }


}
