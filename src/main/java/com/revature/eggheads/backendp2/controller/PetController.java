package com.revature.eggheads.backendp2.controller;

import com.revature.eggheads.backendp2.model.Incubator;
import com.revature.eggheads.backendp2.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    @Autowired
    public PetController(PetService ps) {
        this.petService = ps;
    }

    @GetMapping("/{user-id}")
    public @ResponseBody
    Incubator getPetsByUserId(@PathVariable("user-id") String userId, @RequestParam(name = "pet-id", required=false) String petId){
        if(petId != null){
            return petService.getPetById(Integer.parseInt(userId),Integer.parseInt(petId));
        } else {

        }
        return petService.getPetsByUserId(Integer.valueOf(userId));
    }

    @PostMapping("/{user-id}")
    public @ResponseBody
    Incubator addEggToIncubatorFromTemplate(@RequestParam("user-id") String userId){
        return petService.addPetToUser(Integer.valueOf(userId));
    }

    @DeleteMapping
    public @ResponseBody
    Incubator removeEggFromIncubator(@RequestParam("user-id") String userId, @RequestParam("egg-id") String eggId){
        return petService.removePetFromUser(Integer.valueOf(userId), Integer.valueOf(eggId));
    }
}
