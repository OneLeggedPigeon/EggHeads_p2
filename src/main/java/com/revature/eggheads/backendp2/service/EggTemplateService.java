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
     * This method does not modify the database
     * @param count the number of Eggs to return
     * @return a list of Eggs with size count, or empty is count <= 0
     */
    public List<Egg> getUnregisteredRandomEggs(int count) {
        List<EggTemplate> templates = eggTemplateRepository.findAll();
        List<Egg> eggs = new ArrayList<>();
        Random rand = new Random();
        int s = templates.size();
        if (count > s) {
            // repeats allowed
            for(int i = 0; i < count; i++ ){
                int index = rand.nextInt(s);
                eggs.add(eggService.createEggFromTemplate(templates.get(index)));
            }
        } else if (count == s) {
            // return one for every template (saves operations since we don't need random number generation)
            generateEggs(templates, eggs);
        } else if (count > s/2) {
            // repeats not allowed, randomly remove some to save operations
            for(int i = 0; i < s-count; i++ ){
                int index = rand.nextInt(s-i);
                templates.remove(index);
            }
            generateEggs(templates, eggs);
        } else if (count > 0) {
            // repeats not allowed
            for(int i = s; i > s-count; i-- ){
                int index = rand.nextInt(i);
                EggTemplate t = templates.get(index);
                templates.remove(index);
                eggs.add(eggService.createEggFromTemplate(t));
            }
        }
        return eggs;
    }

    /**
     * As <code>getUnregisteredRandomEggs(int count)</code>, where count = <code>eggTemplateRepository.findAll().size()</code>.
     */
    public List<Egg> getUnregisteredRandomEggs() {
        List<EggTemplate> templates = eggTemplateRepository.findAll();
        List<Egg> eggs = new ArrayList<>();
        int s = templates.size();
        generateEggs(templates, eggs);
        return eggs;
    }

    /**
     * Adds one generated <code>Egg</code> to <code>eggs</code> for each <code>EggTemplate</code> in <code>templates</code>
     * @param templates List of EggTemplates
     * @param eggs List to add created eggs to, one for each template
     */
    private void generateEggs(List<EggTemplate> templates, List<Egg> eggs) {
        for (EggTemplate t : templates) {
            eggs.add(eggService.createEggFromTemplate(t));
        }
    }
}
