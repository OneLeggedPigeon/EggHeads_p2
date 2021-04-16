package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.util.RandomUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class EggService {

    // These will be read into the RandomizationUtil methods
    // They can be replaced by values in the egg Template if we want different randomization for each template
    // 68% of returned values will be within RAND_SCALE of the passed in value
    // returned values can differ by at most RAND_MAX_DEV of the passed in value
    private static final int RAND_SCALE_PERCENTAGE = 10;
    private static final int RAND_MAX_DEV_PERCENTAGE = 20;
    private static final int RAND_SCALE_COLOR = 15;
    private static final int RAND_MAX_DEV_COLOR = 25;

    public Egg createEggFromTemplate(EggTemplate template){

        //TODO: builder design pattern?
        Egg egg = new Egg();
        Timestamp timeCreated = Timestamp.valueOf(LocalDateTime.now());
        // get a random incubation time between 70% and 130% of the template
        int incubationTime = RandomUtil.getRandomIntAutoScaled(template.getIncubationPeriod());
        Timestamp timeComplete = new Timestamp(
                timeCreated.getTime() + (1000L * 60 * 60 * incubationTime));
        egg.setTimeCreated(timeCreated);
        egg.setTimeComplete(timeComplete);
        egg.setReady(false);
        egg.setAnimalType(template.getAnimalType());

        // Randomized Values
        int startingSize = RandomUtil.getRandomIntPercentage(0,RAND_SCALE_PERCENTAGE,RAND_MAX_DEV_PERCENTAGE);
        egg.setStartingSize(startingSize);
        egg.setCurrentSize(startingSize);
        egg.setMaxSize(RandomUtil.getRandomIntPercentage(template.getSize(),RAND_SCALE_PERCENTAGE,RAND_MAX_DEV_PERCENTAGE));
        egg.setRedValue(RandomUtil.getRandomIntColor(template.getRedValue(),RAND_SCALE_PERCENTAGE,RAND_MAX_DEV_PERCENTAGE));
        egg.setGreenValue(RandomUtil.getRandomIntColor(template.getGreenValue(),RAND_SCALE_COLOR,RAND_MAX_DEV_COLOR));
        egg.setBlueValue(RandomUtil.getRandomIntColor(template.getBlueValue(),RAND_SCALE_COLOR,RAND_MAX_DEV_COLOR));

        return egg;
    }
}
