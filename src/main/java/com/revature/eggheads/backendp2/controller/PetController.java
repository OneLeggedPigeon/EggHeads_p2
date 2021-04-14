package com.revature.eggheads.backendp2.controller;

import com.revature.eggheads.backendp2.model.Incubator;
import com.revature.eggheads.backendp2.model.Pet;
import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    List<Pet> getPetsByUserId(@PathVariable("user-id") String userId, @RequestParam(name = "pet-id", required=false) String petId){
        if(petId != null){
            ArrayList<Pet> pets = new ArrayList<>();
            pets.add(petService.getPetById(Integer.parseInt(userId), Integer.parseInt(petId)));
            return pets;
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
