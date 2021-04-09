package com.revature.egghead.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EggTemplate {

    @Id
    @GeneratedValue
    private int id;

    private String eggTemplateName;
    private String animalType;
    private int incubationPeriod;
    private int size;
    private int redValue;
    private int greenValue;
    private int blueValue;
}
