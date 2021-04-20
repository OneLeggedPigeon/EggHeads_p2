package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.model.Incubator;
import com.revature.eggheads.backendp2.model.User;
import com.revature.eggheads.backendp2.repository.IncubatorRepository;
import com.revature.eggheads.backendp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IncubatorService {

    UserService userService;
    EggService eggService;
    EggTemplateService eggTemplateService;
    IncubatorRepository incubatorRepository;

    @Autowired
    public IncubatorService (UserService us, EggService es, EggTemplateService ets, IncubatorRepository ir){
        this.userService = us;
        this.eggService = es;
        this.eggTemplateService = ets;
        this.incubatorRepository = ir;
    }

    public Incubator getOrCreateIncubatorByUserId(Integer id){
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

    public Incubator addEggToIncubatorFromTemplate(Integer userId, Integer templateId){
        EggTemplate template = eggTemplateService.getEggTemplateById(templateId);
        if(template == null) return null;
        Incubator incubator = getOrCreateIncubatorByUserId(userId);
        if(incubator.getEggs().size() < incubator.getCapacity()){
            Egg egg = eggService.createEggFromTemplate(template);
            incubator.getEggs().add(egg);
            egg.setIncubator(incubator);
            return incubatorRepository.save(incubator);
        }
        return incubator;
    }

    public Incubator addEggToIncubator(Integer userId, Egg egg){
        Incubator incubator = getOrCreateIncubatorByUserId(userId);
        if(incubator.getEggs() == null){
            incubator.setEggs(new ArrayList<Egg>());
        }
        if(incubator.getEggs().size() < incubator.getCapacity()){
            incubator.getEggs().add(egg);
            egg.setIncubator(incubator);
            return incubatorRepository.save(incubator);
        }
        return incubator;
    }

    public Incubator removeEggFromIncubator(Integer userId, Integer eggId) {
        Incubator incubator = getOrCreateIncubatorByUserId(userId);
        if(incubator.getEggs().size() == 0) return incubator;
        List<Egg> eggs = incubator.getEggs().stream().filter(egg->egg.getId() != eggId).collect(Collectors.toList());
        incubator.getEggs().clear();
        incubator.getEggs().addAll(eggs);
        return incubatorRepository.save(incubator);
    }
}
