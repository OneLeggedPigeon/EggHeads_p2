package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.repository.EggRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class EggService {

    @Autowired
    EggRepository eggRepository;

    public Egg createEggFromTemplate(EggTemplate template){

        //TODO: builder design pattern?
        Egg egg = new Egg();
        Timestamp timeCreated = Timestamp.valueOf(LocalDateTime.now());
        Timestamp timeComplete = new Timestamp(
                timeCreated.getTime() + (1000L * 60 * 60 * template.getIncubationPeriod()));
        egg.setTimeCreated(timeCreated);
        egg.setTimeComplete(timeComplete);
        egg.setReady(false);
        egg.setAnimalType(template.getAnimalType());

        //TODO: add some randomization to the below
        egg.setStartingSize(0);
        egg.setCurrentSize(0);
        egg.setMaxSize(template.getSize());
        egg.setRedValue(template.getRedValue());
        egg.setGreenValue(template.getGreenValue());
        egg.setBlueValue(template.getBlueValue());

        return egg;
    }
}
