package com.revature.eggheads.backendp2.controller;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.model.Incubator;
import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.repository.IncubatorRepository;
import com.revature.eggheads.backendp2.repository.UserRepository;
import com.revature.eggheads.backendp2.service.IncubatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/incubator")
@CrossOrigin(origins = {"http://localhost:4200", "http://egghead-p2-angular.s3-website.us-east-2.amazonaws.com"})
public class IncubatorController {

    private IncubatorService incubatorService;

    @Autowired
    public IncubatorController(IncubatorService is) {
        this.incubatorService = is;
    }

    @GetMapping("/{user-id}")
    public @ResponseBody
    Incubator getIncubatorByUserId(@PathVariable("user-id") String userId){
        return incubatorService.getOrCreateIncubatorByUserId(Integer.valueOf(userId));
    }

    // TODO: Remove this if we don't end up using it
    @PostMapping("/add-by-template")
    public @ResponseBody
    Incubator addEggToIncubatorFromTemplate(@RequestParam("user-id") String userId, @RequestParam("template-id") String templateId){
        return incubatorService.addEggToIncubatorFromTemplate(Integer.valueOf(userId), Integer.valueOf(templateId));
    }

    /**
     * The Egg in the body of the post shouldn't be in the database or have an id yet.
     * @param userId the user who's incubator the egg will be added to
     * @param egg an Egg to be added to the database
     * @return the incubator the egg is added to
     */
    @PostMapping("/{user-id}")
    public @ResponseBody
    Incubator addEggToIncubator(@PathVariable("user-id") String userId, @RequestBody Egg egg){
        return incubatorService.addEggToIncubator(Integer.valueOf(userId), egg);
    }

    @DeleteMapping("/{user-id}")
    public @ResponseBody
    Incubator removeEggFromIncubator(@PathVariable("user-id") String userId, @RequestParam("egg-id") String eggId){
        return incubatorService.removeEggFromIncubator(Integer.valueOf(userId), Integer.valueOf(eggId));
    }
}