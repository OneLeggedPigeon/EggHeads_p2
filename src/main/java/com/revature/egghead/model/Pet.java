package com.revature.egghead.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String petAdjective;
    private String animalType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
