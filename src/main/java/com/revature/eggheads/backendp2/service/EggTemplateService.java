package com.revature.eggheads.backendp2.service;

import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.repository.EggTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EggTemplateService {

    @Autowired
    EggTemplateRepository eggTemplateRepository;

    public EggTemplate getEggTemplateById(Integer id){
        return eggTemplateRepository.findById(id).orElse(null);
    }

}
