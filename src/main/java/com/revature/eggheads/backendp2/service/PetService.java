package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.*;
import com.revature.eggheads.backendp2.repository.IncubatorRepository;
import com.revature.eggheads.backendp2.repository.PetRepository;
import com.revature.eggheads.backendp2.repository.UserRepository;
import com.revature.eggheads.backendp2.util.AdjectiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    // TODO: have this adjective type be generated based on egg interactions or a new egg value
    private final String ADJECTIVE_TYPE = "emotion";

    UserService userService;
    UserRepository userRepository;
    IncubatorService incubatorService;
    IncubatorRepository incubatorRepository;
    PetRepository petRepository;
    AdjectiveUtil adjectiveUtil;

    @Autowired
    public PetService(UserService us, UserRepository up, IncubatorService is, IncubatorRepository ir, PetRepository pr, AdjectiveUtil au){
        this.userService = us;
        this.userRepository = up;
        this.incubatorService = is;
        this.incubatorRepository = ir;
        this.adjectiveUtil = au;
    }

    public List<Pet> getPetsByUserId(int userId) {
        User user = userService.getUserById(userId);
        if(user == null){
            return null;
        }
        return user.getPets();
    }

    public Pet getPetById(int userId, int petId) {
        List<Pet> pets = getPetsByUserId(userId);
        if(pets == null) return null;
        Pet pet = null;
        for(Pet p : pets) {
            if (p.getId() == petId) {
                pet = p;
                break; // stop checking here because there should only be max one pet with this id
            }
        }
        return pet;
    }

    public Pet addPetToUser(int userId, int eggId, String name) {
        User user = userService.getUserById(userId);
        List<Egg> eggs = user.getIncubator().getEggs();
        Egg egg = null;
        for(Egg e : eggs) {
            if (e.getId() == eggId) {
                egg = e;
                break; // stop checking here because there should only be max one egg with this id
            }
        }
        if(egg == null) return null; // if the egg that needs hatching isn't there, we shouldn't create the pet
        Pet pet = new Pet();
        pet.setName(name);
        pet.setUser(user);
        pet.setAnimalType(egg.getAnimalType());
        pet.setPetAdjective(AdjectiveUtil.generateAdjective(ADJECTIVE_TYPE));
        incubatorService.removeEggFromIncubator(userId, eggId);
        return petRepository.save(pet);
    }

    public User removePetFromUser(Integer userId, Integer petId) {
        User user = userService.getUserById(userId);
        if(user.getPets().size() == 0) return user;
        List<Pet> pets = user.getPets().stream().filter(pet->pet.getId() != petId).collect(Collectors.toList());
        user.getPets().clear();
        user.getPets().addAll(pets);
        return userRepository.save(user);
    }
}
