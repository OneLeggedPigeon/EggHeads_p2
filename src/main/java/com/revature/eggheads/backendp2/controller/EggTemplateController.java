package com.revature.eggheads.backendp2.controller;

import com.revature.eggheads.backendp2.model.Egg;
import com.revature.eggheads.backendp2.model.EggTemplate;
import com.revature.eggheads.backendp2.repository.EggTemplateRepository;
import com.revature.eggheads.backendp2.service.EggTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/egg_template")
public class EggTemplateController {

    private EggTemplateRepository repo;
    private EggTemplateService service;

    @Autowired
    public EggTemplateController(EggTemplateRepository repo, EggTemplateService service){
        this.repo = repo;
        this.service = service;
    }

    @PostMapping
    public @ResponseBody EggTemplate save(@RequestBody EggTemplate et){return repo.save(et);}

    @GetMapping
    public @ResponseBody
    List<EggTemplate> getEggTemplate(){return repo.findAll();}

    @GetMapping("/market")// TODO: rename this endpoint based on frontend implementation
    public @ResponseBody
    List<Egg> getRandomEggsFromTemplate(@RequestParam(name = "count", required = false) String count){
        if(count == null){
            return service.getUnregisteredRandomEggs();
        } else {
            return service.getUnregisteredRandomEggs(Integer.parseInt(count));
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody
    EggTemplate getEggTemplate(@PathVariable("id") String id){return repo.findById (Integer.valueOf (id)).orElse (null);}

    @GetMapping(value = "/animal_type/{type}")
    public @ResponseBody List<EggTemplate> getEggTemplateByAnimalType(@PathVariable("type") String type){return repo.findByAnimalType(type);}

    @GetMapping(value = "/incubation_period/{period}")
    public @ResponseBody
    List<EggTemplate> getEggTemplateListByIncubationPeropd(@PathVariable("period") String period){
        return repo.findByIncubationPeriod(Integer.parseInt(period));
    }

    @GetMapping(value = "/incubation_period_less_than_or_equal_to/{period}")
    public @ResponseBody
    List<EggTemplate> getEggTemplateListByIncubationPeriodWithin(@PathVariable("period") String period){return repo.findByIncubationPeriodLessThanEqual(Integer.parseInt(period));}

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<HttpStatus> delete(@PathVariable("id") String id){
        repo.findById(Integer.valueOf(id)).ifPresent(
                u-> repo.delete(u)
        );
        return ResponseEntity.ok(HttpStatus.OK);
    }

}