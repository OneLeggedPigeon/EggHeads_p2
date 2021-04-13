package com.revature.eggheads.backendp2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Egg {
    @Id
    @GeneratedValue
    private int id;

    private boolean isReady;
    private Timestamp timeCreated;
    private Timestamp timeComplete;
    private int startingSize;
    private int maxSize;
    private int currentSize;
    private int redValue;
    private int greenValue;
    private int blueValue;
    private String animalType;


    @ManyToOne
    @JoinColumn(name = "incubator_id")
    @JsonIgnore
    private Incubator incubator;
}
