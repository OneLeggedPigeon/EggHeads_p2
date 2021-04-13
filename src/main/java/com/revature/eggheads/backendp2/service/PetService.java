package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.model.Incubator;
import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.repository.IncubatorRepository;
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
        if(user.getIncubator() == null){
            user.setIncubator(new Incubator());
            user = userService.saveUser(user);
        }
        return user.getIncubator();
    }

    public Incubator getPetById(int userId, int petId) {
        return null;
    }
}
