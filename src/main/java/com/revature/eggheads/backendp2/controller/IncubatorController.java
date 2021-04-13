package com.revature.eggheads.backendp2.controller;

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

    @PostMapping
    public @ResponseBody
    Incubator addEggToIncubatorFromTemplate(@RequestParam("user-id") String userId, @RequestParam("template-id") String templateId){
        return incubatorService.addEggToIncubatorFromTemplate(Integer.valueOf(userId), Integer.valueOf(templateId));
    }

    @DeleteMapping
    public @ResponseBody
    Incubator removeEggFromIncubator(@RequestParam("user-id") String userId, @RequestParam("egg-id") String eggId){
        return incubatorService.removeEggFromIncubator(Integer.valueOf(userId), Integer.valueOf(eggId));
    }
}
