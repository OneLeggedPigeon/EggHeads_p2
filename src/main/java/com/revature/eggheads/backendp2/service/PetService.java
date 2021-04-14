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

    UserService userService;
    EggService eggService;
    EggTemplateService eggTemplateService;
    IncubatorRepository incubatorRepository;

    @Autowired
    public PetService(UserService us, EggService es, EggTemplateService ets, IncubatorRepository ir){
        this.userService = us;
        this.eggService = es;
        this.eggTemplateService = ets;
        this.incubatorRepository = ir;
    }

    public Incubator getPetsByUserId(Integer id){
        User user = userService.getUserById(id);
        if(user == null){
            return null;
        }
        return user.getPets();
    }

    public Incubator getPetById(int userId, int petId) {
        return null;
    }
}
