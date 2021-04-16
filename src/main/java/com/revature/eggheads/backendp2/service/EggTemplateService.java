package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.repository.EggTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EggTemplateService {

    @Autowired
    EggTemplateRepository eggTemplateRepository;

    @Autowired
    EggService eggService;

    public EggTemplate getEggTemplateById(Integer id){
        return eggTemplateRepository.findById(id).orElse(null);
    }

    /**
     * does not modify the database
     */
    public List<Egg> getUnregisteredRandomEggs(int count) {
        List<EggTemplate> templates = eggTemplateRepository.findAll();
        List<Egg> eggs = new ArrayList<>();
        Random rand = new Random();
        int s = templates.size();//TODO handle count > s
        for(int i = s; i > s-count; i-- ){
            int index = rand.nextInt(i);
            EggTemplate t = templates.get(index);
            templates.remove(index);
            eggs.add(eggService.createEggFromTemplate(t));
        }
        return eggs;
    }

    /**
     * does not modify the database
     */
    public List<Egg> getUnregisteredRandomEggs() {
        List<EggTemplate> templates = eggTemplateRepository.findAll();
        List<Egg> eggs = new ArrayList<>();
        int s = templates.size();
        for(EggTemplate t : templates){
            eggs.add(eggService.createEggFromTemplate(t));
        }
        return eggs;
    }
}
